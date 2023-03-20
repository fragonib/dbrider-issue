package dbrider.spring

import com.github.database.rider.core.configuration.DataSetConfig
import com.github.database.rider.core.configuration.ExpectedDataSetConfig
import com.github.database.rider.spring.api.DBRider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject

import javax.sql.DataSource
import java.time.LocalDateTime

import static com.github.database.rider.core.dsl.RiderDSL.withConnection

@TxDBTest
@DBRider
@Import(value = [Table2Repository])
class Table2RepositoryIT extends Specification {

    @Autowired
    DataSource ds

    @Subject
    @Autowired
    Table2Repository repository

    @Sql(scripts = ["/test/table2_data.sql"])
    def "should find last record"() {

        when:
        def last = repository.findLast("CHANGED")

        then:
        last.id.toString() == '0cd88fb4-0492-4d0e-811a-9025f832f03d'

    }

    @Sql(scripts = "/test/table2_data_empty.sql")
    def "should store new records"() {

        given:
        def changedRecord = new Record(
                UUID.randomUUID(),
                "CHANGED",
                LocalDateTime.parse('2023-01-18T10:00:00')
        )
        def otherRecord = new Record(
                UUID.randomUUID(),
                "OTHER",
                LocalDateTime.parse('2023-01-18T11:00:00')
        )

        when:
        repository.save(changedRecord)
        repository.save(otherRecord)

        then:
        withConnection(DataSourceUtils.getConnection(ds))
                .withDataSetConfig(new DataSetConfig("/test/table2_data_save.expected.yml"))
                .expectDataSet(new ExpectedDataSetConfig().orderBy("type"))

    }

}
