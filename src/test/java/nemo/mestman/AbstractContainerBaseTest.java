package nemo.mestman;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;
import nemo.mestman.domain.member.Member;
import nemo.mestman.domain.member.MemberRepository;
import nemo.mestman.domain.roadmap.RoadMap;
import nemo.mestman.domain.roadmap.RoadMapRepository;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureWebTestClient
public class AbstractContainerBaseTest {
	private static final String REDIS_IMAGE = "redis:7-alpine";

	private static final GenericContainer REDIS_CONTAINER;

	static {
		REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(6379)
			.withReuse(true);
		REDIS_CONTAINER.start();
	}

	@DynamicPropertySource
	public static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379).toString());
	}

	@Autowired
	protected MemberRepository memberRepository;

	@Autowired
	protected RoadMapRepository roadMapRepository;

	@AfterEach
	void tearDown() {
		roadMapRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
	}

	protected Member createMember() {
		return Member.create("user1@gmail.com", "유저1", "password1");
	}

	protected RoadMap createRoadMap(Member member) {
		return RoadMap.create("로드맵1", "히어로", member);
	}
}
