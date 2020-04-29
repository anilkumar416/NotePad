package com.example.notepad.di

import com.example.notepad.ui.AddFragment
import com.example.notepad.ui.EditFragment
import com.example.notepad.ui.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeAddFragment(): AddFragment

    @ContributesAndroidInjector
    abstract fun contributeEditFragment(): EditFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment


}