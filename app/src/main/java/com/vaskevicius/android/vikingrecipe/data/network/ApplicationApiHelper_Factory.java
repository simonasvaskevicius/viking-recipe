package com.vaskevicius.android.vikingrecipe.data.network;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationApiHelper_Factory implements Factory<ApplicationApiHelper> {
  @Override
  public ApplicationApiHelper get() {
    return newInstance();
  }

  public static ApplicationApiHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ApplicationApiHelper newInstance() {
    return new ApplicationApiHelper();
  }

  private static final class InstanceHolder {
    private static final ApplicationApiHelper_Factory INSTANCE = new ApplicationApiHelper_Factory();
  }
}
