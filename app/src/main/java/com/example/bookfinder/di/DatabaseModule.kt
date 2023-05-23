package com.example.bookfinder.di

import android.content.Context
import androidx.room.Room
import com.example.bookfinder.data.local.BookDatabase
import com.example.bookfinder.data.local.dao.BookDao
import com.example.bookfinder.data.local.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase {
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            "book_database"
        ).build()
    }

    @Provides
    fun provideBookDao(database: BookDatabase): BookDao {
        return database.bookDao()
    }

    @Provides
    fun provideNoteDao(database: BookDatabase): NoteDao {
        return database.noteDao()
    }
}