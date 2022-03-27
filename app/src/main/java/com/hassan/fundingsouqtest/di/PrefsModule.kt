package com.example.doctorconsultationapp.di

import android.content.Context
import com.hassan.fundingsouqtest.utilities.db.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ActivityRetainedComponent::class)
@Module
class PrefsModule {

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences = UserPreferences(context)
}