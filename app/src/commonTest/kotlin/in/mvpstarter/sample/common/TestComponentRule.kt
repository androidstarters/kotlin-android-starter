package `in`.mvpstarter.sample.common

import android.content.Context

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

import `in`.mvpstarter.sample.MvpStarterApplication
import `in`.mvpstarter.sample.common.injection.component.DaggerTestComponent
import `in`.mvpstarter.sample.common.injection.component.TestComponent
import `in`.mvpstarter.sample.common.injection.module.ApplicationTestModule
import `in`.mvpstarter.sample.data.DataManager

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
class TestComponentRule(val context: Context) : TestRule {

    val testComponent: TestComponent

    init {
        val application = MvpStarterApplication.get(context)
        testComponent = DaggerTestComponent.builder()
                .applicationTestModule(ApplicationTestModule(application))
                .build()
    }

    val mockDataManager: DataManager
        get() = testComponent.dataManager()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                val application = MvpStarterApplication.get(context)
                application.component = testComponent
                base.evaluate()
                application.component = null
            }
        }
    }
}