package com.alex.phonebook.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

@Database(entities = {Contact.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase class";

    private static AppDatabase INSTANCE;

    private static Disposable disposable;

    public abstract ContactDao contactDao();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "contactsDB")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            disposable = fillingDB()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableCompletableObserver() {
                                        @Override
                                        public void onComplete() {
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.e(TAG, e.getMessage(), e);
                                        }
                                    });
                            disposable.dispose();
                        }
                    })
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

    private static void insertSomeRecords() {
        INSTANCE.contactDao().insert(new Contact("Nick", "White", 227896655, "Home"));
        INSTANCE.contactDao().insert(new Contact("Sam", "Winston", 114477965, "Work"));
        INSTANCE.contactDao().insert(new Contact("John", "Smith", 224568877, "Work"));
    }

    private static Completable fillingDB() {
        return Completable.fromAction(AppDatabase::insertSomeRecords);
    }
}