import com.organeco.model.remote.ApiService
import com.organeco.model.remote.factory.ApiServiceFactory
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test

class ApiServiceFactoryTest {

    @Before
    fun setUp() {
        // Reset the singleton instance before each test
        val field = ApiServiceFactory::class.java.getDeclaredField("instance")
        field.isAccessible = true
        field.set(null, null)
    }

    @Test
    fun testSingleton() {
        val instance1 = ApiServiceFactory.getInstance(ApiServiceFactory.ApiType.DEFAULT)
        val instance2 = ApiServiceFactory.getInstance(ApiServiceFactory.ApiType.DEFAULT)

        println("instance1: $instance1")
        println("instance2: $instance2")
    }
}