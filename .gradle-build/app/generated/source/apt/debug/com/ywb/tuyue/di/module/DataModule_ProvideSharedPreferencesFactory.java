package com.ywb.tuyue.di.module;

import com.blankj.utilcode.util.SPUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataModule_ProvideSharedPreferencesFactory implements Factory<SPUtils> {
  private final DataModule module;

  public DataModule_ProvideSharedPreferencesFactory(DataModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public SPUtils get() {
    return Preconditions.checkNotNull(
        module.provideSharedPreferences(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SPUtils> create(DataModule module) {
    return new DataModule_ProvideSharedPreferencesFactory(module);
  }

  /** Proxies {@link DataModule#provideSharedPreferences()}. */
  public static SPUtils proxyProvideSharedPreferences(DataModule instance) {
    return instance.provideSharedPreferences();
  }
}
