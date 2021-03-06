package com.sean.blog.di

import com.sean.blog.di.auth.AuthFragmentBuildersModule
import com.sean.blog.di.auth.AuthModule
import com.sean.blog.di.auth.AuthScope
import com.sean.blog.di.auth.AuthViewModelModule
import com.sean.blog.ui.auth.AuthActivity
import com.sean.blog.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}