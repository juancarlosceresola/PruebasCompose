package com.example.pruebascompose.data.di

import android.content.Context
import com.example.pruebascompose.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class StringModule{

    @Provides
    @Named("bearerToken")
    fun provideBearerToken(@ApplicationContext context: Context) =
        context.getString(R.string.token)

}