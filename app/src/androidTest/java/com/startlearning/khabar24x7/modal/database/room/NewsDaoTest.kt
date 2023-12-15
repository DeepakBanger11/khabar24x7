package com.startlearning.khabar24x7.modal.database.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.startlearning.khabar24x7.modal.data.TableArticle
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class LikedArticleDaoTest {
    private lateinit var database: NewsDatabase
    private lateinit var newDao: NewsDao
    private val articleA = TableArticle(0, "A", "b", "c", "1", "d", "e", "f")
    private val articleB = TableArticle(1, "b", "h", "k", "5", "f", "e", "f")
    private val articleC = TableArticle(2, "C", "r", "g", "8", "u", "e", "f")
    private val articleD = TableArticle(3, "z", "z", "z", "z", "z", "e", "f")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        newDao = database.newsDao()

        // Insert articles in non-alphabetical order to test that results are sorted by title
        newDao.addArticles(articleA)
        newDao.addArticles(articleC)
        newDao.addArticles(articleB)
        // likedNewsDao.insertLikedArticle(articleD)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAllLikedArticles() = runBlocking {
        val latch = CountDownLatch(1) // Creating a latch to await LiveData updates
        var likedArticlesResult: List<TableArticle>? = null

        val observer = Observer<List<TableArticle>> { articles ->
            likedArticlesResult = articles
            latch.countDown() // Signal that the LiveData value has been received
        }

        newDao.getAllArticles().observeForever(observer)

        // Wait for the value to be received, or timeout after a certain time
        latch.await(2, TimeUnit.SECONDS) // Adjust timeout duration if needed

        // Unregister the observer
        newDao.getAllArticles().removeObserver(observer)

        assertThat(likedArticlesResult?.size ?: 0, equalTo(2))
    }

    @Test
    fun testInsertLikedArticle() = runBlocking {
        newDao.addArticles(articleD)
        val likedArticlesCount = newDao.getArticlesCount()

        assertThat(likedArticlesCount, equalTo(3))

    }

    @Test
    fun testDeleteLikedArticle() = runBlocking {
        newDao.deleteArticleByTitle("d")
        val likedArticlesCount = newDao.getArticlesCount()

        assertThat(likedArticlesCount, equalTo(1))

    }
}
