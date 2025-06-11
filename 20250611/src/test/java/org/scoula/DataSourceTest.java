package org.scoula;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * DataSource 및 커넥션 풀 테스트 클래스
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            log.info("=== DataSource 커넥션 풀 연결 성공 ===");
            log.info("연결 객체: {}", con);
            log.info("연결 클래스: {}", con.getClass().getName());

            if (con.getClass().getName().contains("Hikari")) {
                log.info("✅ HikariCP 커넥션 풀이 정상 작동중입니다.");
            }

        } catch (SQLException e) {
            log.error("❌ DataSource 연결 실패: {}", e.getMessage());
            throw e;
        }
    }
}
