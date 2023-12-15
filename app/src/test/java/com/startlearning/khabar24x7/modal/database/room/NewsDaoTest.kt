import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.startlearning.khabar24x7.modal.data.TableArticle
import com.startlearning.khabar24x7.modal.database.room.NewsDao
import com.startlearning.khabar24x7.modal.database.room.NewsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsDaoTest {
    private lateinit var database: NewsDatabase
    private lateinit var dao: NewsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.newsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun checkEntriesIntoDatabase() {
        runBlocking {

            val article = TableArticle(
                id = 1,
                author = "Author",
                content = "Content",
                description = "Description",
                publishedAt = "2023-12-14T12:00:00Z",
                title = "Title",
                url = "https://example.com",
                urlToImage = "https://example.com/image.jpg"
            )

            dao.addArticles(article)

            val articles = dao.getAllArticles().value
            articles?.let { assert(it.contains(article)) }
        }
    }
}
