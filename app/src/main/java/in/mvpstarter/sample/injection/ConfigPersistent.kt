package `in`.mvpstarter.sample.injection


import `in`.mvpstarter.sample.injection.component.ConfigPersistentComponent
import javax.inject.Scope

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * [ConfigPersistentComponent]
 */
@Scope @Retention annotation class ConfigPersistent