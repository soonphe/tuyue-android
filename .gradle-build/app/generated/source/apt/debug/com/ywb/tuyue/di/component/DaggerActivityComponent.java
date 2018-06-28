package com.ywb.tuyue.di.component;

import android.app.Activity;
import android.content.Context;
import com.ywb.tuyue.api.AppApi;
import com.ywb.tuyue.di.module.ActivityModule;
import com.ywb.tuyue.di.module.ActivityModule_ProvideActivityFactory;
import com.ywb.tuyue.ui.advertise.AdvertiseDetailActivity;
import com.ywb.tuyue.ui.main.MainActivity;
import com.ywb.tuyue.ui.main.MainActivity_MembersInjector;
import com.ywb.tuyue.ui.main.MainPresenter;
import com.ywb.tuyue.ui.main.MainPresenter_Factory;
import com.ywb.tuyue.ui.splash.SplashActivity;
import com.ywb.tuyue.ui.unlock.UnlockActivity;
import com.ywb.tuyue.ui.unlock.UnlockActivity_MembersInjector;
import com.ywb.tuyue.ui.unlock.UnlockPresenter;
import com.ywb.tuyue.ui.unlock.UnlockPresenter_Factory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerActivityComponent implements ActivityComponent {
  private Provider<Activity> provideActivityProvider;

  private Provider<Context> getContextProvider;

  private Provider<AppApi> getAccountApiProvider;

  private Provider<MainPresenter> mainPresenterProvider;

  private MembersInjector<MainActivity> mainActivityMembersInjector;

  private Provider<UnlockPresenter> unlockPresenterProvider;

  private MembersInjector<UnlockActivity> unlockActivityMembersInjector;

  private DaggerActivityComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideActivityProvider =
        DoubleCheck.provider(ActivityModule_ProvideActivityFactory.create(builder.activityModule));

    this.getContextProvider =
        new com_ywb_tuyue_di_component_ApplicationComponent_getContext(
            builder.applicationComponent);

    this.getAccountApiProvider =
        new com_ywb_tuyue_di_component_ApplicationComponent_getAccountApi(
            builder.applicationComponent);

    this.mainPresenterProvider =
        MainPresenter_Factory.create(
            MembersInjectors.<MainPresenter>noOp(), getContextProvider, getAccountApiProvider);

    this.mainActivityMembersInjector = MainActivity_MembersInjector.create(mainPresenterProvider);

    this.unlockPresenterProvider =
        DoubleCheck.provider(
            UnlockPresenter_Factory.create(
                MembersInjectors.<UnlockPresenter>noOp(),
                getContextProvider,
                getAccountApiProvider));

    this.unlockActivityMembersInjector =
        UnlockActivity_MembersInjector.create(unlockPresenterProvider);
  }

  @Override
  public Activity getActivity() {
    return provideActivityProvider.get();
  }

  @Override
  public void inject(MainActivity activity) {
    mainActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(SplashActivity activity) {
    MembersInjectors.<SplashActivity>noOp().injectMembers(activity);
  }

  @Override
  public void inject(UnlockActivity unlockActivity) {
    unlockActivityMembersInjector.injectMembers(unlockActivity);
  }

  @Override
  public void inject(AdvertiseDetailActivity advertiseDetailActivity) {
    MembersInjectors.<AdvertiseDetailActivity>noOp().injectMembers(advertiseDetailActivity);
  }

  public static final class Builder {
    private ActivityModule activityModule;

    private ApplicationComponent applicationComponent;

    private Builder() {}

    public ActivityComponent build() {
      if (activityModule == null) {
        throw new IllegalStateException(ActivityModule.class.getCanonicalName() + " must be set");
      }
      if (applicationComponent == null) {
        throw new IllegalStateException(
            ApplicationComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerActivityComponent(this);
    }

    public Builder activityModule(ActivityModule activityModule) {
      this.activityModule = Preconditions.checkNotNull(activityModule);
      return this;
    }

    public Builder applicationComponent(ApplicationComponent applicationComponent) {
      this.applicationComponent = Preconditions.checkNotNull(applicationComponent);
      return this;
    }
  }

  private static class com_ywb_tuyue_di_component_ApplicationComponent_getContext
      implements Provider<Context> {
    private final ApplicationComponent applicationComponent;

    com_ywb_tuyue_di_component_ApplicationComponent_getContext(
        ApplicationComponent applicationComponent) {
      this.applicationComponent = applicationComponent;
    }

    @Override
    public Context get() {
      return Preconditions.checkNotNull(
          applicationComponent.getContext(),
          "Cannot return null from a non-@Nullable component method");
    }
  }

  private static class com_ywb_tuyue_di_component_ApplicationComponent_getAccountApi
      implements Provider<AppApi> {
    private final ApplicationComponent applicationComponent;

    com_ywb_tuyue_di_component_ApplicationComponent_getAccountApi(
        ApplicationComponent applicationComponent) {
      this.applicationComponent = applicationComponent;
    }

    @Override
    public AppApi get() {
      return Preconditions.checkNotNull(
          applicationComponent.getAccountApi(),
          "Cannot return null from a non-@Nullable component method");
    }
  }
}
