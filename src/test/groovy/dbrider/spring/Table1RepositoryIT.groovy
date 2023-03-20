package dbrider.spring

import com.github.database.rider.spring.api.DBRider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject

@TxDBTest
@DBRider
@Sql(scripts = ["/test/table1_data.sql"])
@Import(value = [Table1Repository])
class Table1RepositoryIT extends Specification {

    @Subject
    @Autowired
    Table1Repository repository

    def "should find last record"() {

        when:
        def last = repository.findLast("CHANGED")

        then:
        last.id.toString() == '589f0a44-6ed3-4ed5-8ffb-402f54dfb92e'

    }

}
