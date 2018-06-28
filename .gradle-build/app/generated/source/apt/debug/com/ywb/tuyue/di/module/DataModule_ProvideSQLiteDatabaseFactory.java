package com.ywb.tuyue.di.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataModule_ProvideSQLiteDatabaseFactory implements Factory<SQLiteDatabase> {
  private final DataModule module;

  private final Provider<Context> contextProvider;

  public DataModule_ProvideSQLiteDatabaseFactory(
      DataModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public SQLiteDatabase get() {
    return Preconditions.checkNotNull(
        module.provideSQLiteDatabase(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SQLiteDatabase> create(
      DataModule module, Provider<Context> contextProvider) {
    return new DataModule_ProvideSQLiteDatabaseFactory(module, contextProvider);
  }

  /** Proxies {@link DataModule#provideSQLiteDatabase(Context)}. */
  public static SQLiteDatabase proxyProvideSQLiteDatabase(DataModule instance, Context context) {
    return instance.provideSQLiteDatabase(context);
  }
}
