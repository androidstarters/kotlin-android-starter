package `in`.mvpstarter.sample.injection


import javax.inject.Scope

import `in`.mvpstarter.sample.injection.component.ConfigPersistentComponent

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * [ConfigPersistentComponent]
 */
@Scope @Retention annotation class ConfigPersistent