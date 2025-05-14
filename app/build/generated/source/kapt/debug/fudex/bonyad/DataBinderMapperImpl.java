package fudex.bonyad;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import fudex.bonyad.databinding.ActivecodeviewModelBindingImpl;
import fudex.bonyad.databinding.ChangelanguageviewModelBindingImpl;
import fudex.bonyad.databinding.ContactusviewModelBindingImpl;
import fudex.bonyad.databinding.EditpasswordViewModelBindingImpl;
import fudex.bonyad.databinding.EditphoneViewModelBindingImpl;
import fudex.bonyad.databinding.EdituserViewModelBindingImpl;
import fudex.bonyad.databinding.ForgetviewModelBindingImpl;
import fudex.bonyad.databinding.LoginviewModelBindingImpl;
import fudex.bonyad.databinding.ProfileviewModelBindingImpl;
import fudex.bonyad.databinding.ResetpassviewModelBindingImpl;
import fudex.bonyad.databinding.SelecttypeviewModelBindingImpl;
import fudex.bonyad.databinding.StaticpageviewModelBindingImpl;
import fudex.bonyad.databinding.UserhomeviewModelBindingImpl;
import fudex.bonyad.databinding.UserprofileViewModelBindingImpl;
import fudex.bonyad.databinding.UserregisterviewModelBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYACTIVEUSER = 1;

  private static final int LAYOUT_ACTIVITYCHANGELANGUE = 2;

  private static final int LAYOUT_ACTIVITYCONTACTUS = 3;

  private static final int LAYOUT_ACTIVITYEDITPASS = 4;

  private static final int LAYOUT_ACTIVITYEDITPHONE = 5;

  private static final int LAYOUT_ACTIVITYEDITUSERDATA = 6;

  private static final int LAYOUT_ACTIVITYFORGETPASS = 7;

  private static final int LAYOUT_ACTIVITYLOGIN = 8;

  private static final int LAYOUT_ACTIVITYPROFILE = 9;

  private static final int LAYOUT_ACTIVITYRESETPASSWORD = 10;

  private static final int LAYOUT_ACTIVITYSELECTTYPE = 11;

  private static final int LAYOUT_ACTIVITYSTATICPAGE = 12;

  private static final int LAYOUT_ACTIVITYUSERHOME = 13;

  private static final int LAYOUT_ACTIVITYUSERREGISTER = 14;

  private static final int LAYOUT_FRAGMENTUSERPROFILE = 15;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(15);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_activeuser, LAYOUT_ACTIVITYACTIVEUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_changelangue, LAYOUT_ACTIVITYCHANGELANGUE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_contactus, LAYOUT_ACTIVITYCONTACTUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editpass, LAYOUT_ACTIVITYEDITPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editphone, LAYOUT_ACTIVITYEDITPHONE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_edituserdata, LAYOUT_ACTIVITYEDITUSERDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_forgetpass, LAYOUT_ACTIVITYFORGETPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_resetpassword, LAYOUT_ACTIVITYRESETPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_selecttype, LAYOUT_ACTIVITYSELECTTYPE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_staticpage, LAYOUT_ACTIVITYSTATICPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userhome, LAYOUT_ACTIVITYUSERHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userregister, LAYOUT_ACTIVITYUSERREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_userprofile, LAYOUT_FRAGMENTUSERPROFILE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYACTIVEUSER: {
          if ("layout/activity_activeuser_0".equals(tag)) {
            return new ActivecodeviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_activeuser is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCHANGELANGUE: {
          if ("layout/activity_changelangue_0".equals(tag)) {
            return new ChangelanguageviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_changelangue is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCONTACTUS: {
          if ("layout/activity_contactus_0".equals(tag)) {
            return new ContactusviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_contactus is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEDITPASS: {
          if ("layout/activity_editpass_0".equals(tag)) {
            return new EditpasswordViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_editpass is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEDITPHONE: {
          if ("layout/activity_editphone_0".equals(tag)) {
            return new EditphoneViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_editphone is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEDITUSERDATA: {
          if ("layout/activity_edituserdata_0".equals(tag)) {
            return new EdituserViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_edituserdata is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYFORGETPASS: {
          if ("layout/activity_forgetpass_0".equals(tag)) {
            return new ForgetviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_forgetpass is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new LoginviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROFILE: {
          if ("layout/activity_profile_0".equals(tag)) {
            return new ProfileviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYRESETPASSWORD: {
          if ("layout/activity_resetpassword_0".equals(tag)) {
            return new ResetpassviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_resetpassword is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSELECTTYPE: {
          if ("layout/activity_selecttype_0".equals(tag)) {
            return new SelecttypeviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_selecttype is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSTATICPAGE: {
          if ("layout/activity_staticpage_0".equals(tag)) {
            return new StaticpageviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_staticpage is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUSERHOME: {
          if ("layout/activity_userhome_0".equals(tag)) {
            return new UserhomeviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_userhome is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUSERREGISTER: {
          if ("layout/activity_userregister_0".equals(tag)) {
            return new UserregisterviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_userregister is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTUSERPROFILE: {
          if ("layout/fragment_userprofile_0".equals(tag)) {
            return new UserprofileViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_userprofile is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "model");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(15);

    static {
      sKeys.put("layout/activity_activeuser_0", fudex.bonyad.R.layout.activity_activeuser);
      sKeys.put("layout/activity_changelangue_0", fudex.bonyad.R.layout.activity_changelangue);
      sKeys.put("layout/activity_contactus_0", fudex.bonyad.R.layout.activity_contactus);
      sKeys.put("layout/activity_editpass_0", fudex.bonyad.R.layout.activity_editpass);
      sKeys.put("layout/activity_editphone_0", fudex.bonyad.R.layout.activity_editphone);
      sKeys.put("layout/activity_edituserdata_0", fudex.bonyad.R.layout.activity_edituserdata);
      sKeys.put("layout/activity_forgetpass_0", fudex.bonyad.R.layout.activity_forgetpass);
      sKeys.put("layout/activity_login_0", fudex.bonyad.R.layout.activity_login);
      sKeys.put("layout/activity_profile_0", fudex.bonyad.R.layout.activity_profile);
      sKeys.put("layout/activity_resetpassword_0", fudex.bonyad.R.layout.activity_resetpassword);
      sKeys.put("layout/activity_selecttype_0", fudex.bonyad.R.layout.activity_selecttype);
      sKeys.put("layout/activity_staticpage_0", fudex.bonyad.R.layout.activity_staticpage);
      sKeys.put("layout/activity_userhome_0", fudex.bonyad.R.layout.activity_userhome);
      sKeys.put("layout/activity_userregister_0", fudex.bonyad.R.layout.activity_userregister);
      sKeys.put("layout/fragment_userprofile_0", fudex.bonyad.R.layout.fragment_userprofile);
    }
  }
}
