package fudex.bonyad;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import fudex.bonyad.databinding.ActivecodeviewModelBindingImpl;
import fudex.bonyad.databinding.AddserviceviewModelBindingImpl;
import fudex.bonyad.databinding.AppointmentmanageviewModelBindingImpl;
import fudex.bonyad.databinding.AvailbiltyviewModelBindingImpl;
import fudex.bonyad.databinding.CertificatviewModelBindingImpl;
import fudex.bonyad.databinding.ChangelanguageviewModelBindingImpl;
import fudex.bonyad.databinding.ContactusviewModelBindingImpl;
import fudex.bonyad.databinding.DayviewModelBindingImpl;
import fudex.bonyad.databinding.EditpasswordViewModelBindingImpl;
import fudex.bonyad.databinding.EditphoneViewModelBindingImpl;
import fudex.bonyad.databinding.EdittechnicalViewModelBindingImpl;
import fudex.bonyad.databinding.EdituserViewModelBindingImpl;
import fudex.bonyad.databinding.ForgetviewModelBindingImpl;
import fudex.bonyad.databinding.ImagesviewModelBindingImpl;
import fudex.bonyad.databinding.LoginviewModelBindingImpl;
import fudex.bonyad.databinding.PlanViewBindingImpl;
import fudex.bonyad.databinding.PlanviewModelBindingImpl;
import fudex.bonyad.databinding.ProfileviewModelBindingImpl;
import fudex.bonyad.databinding.ResetpassviewModelBindingImpl;
import fudex.bonyad.databinding.SelecttypeviewModelBindingImpl;
import fudex.bonyad.databinding.ServiceviewModelBindingImpl;
import fudex.bonyad.databinding.StaticpageviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalhomeviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalimagesviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalprofileViewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalregisterviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalservicedetailsviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalserviceviewModelBindingImpl;
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

  private static final int LAYOUT_ACTIVITYADDSERVICE = 2;

  private static final int LAYOUT_ACTIVITYAPPOINTMENTMANAGECTIVITY = 3;

  private static final int LAYOUT_ACTIVITYCHANGELANGUE = 4;

  private static final int LAYOUT_ACTIVITYCONTACTUS = 5;

  private static final int LAYOUT_ACTIVITYEDITPASS = 6;

  private static final int LAYOUT_ACTIVITYEDITPHONE = 7;

  private static final int LAYOUT_ACTIVITYEDITTECHNICALDATA = 8;

  private static final int LAYOUT_ACTIVITYEDITUSERDATA = 9;

  private static final int LAYOUT_ACTIVITYFORGETPASS = 10;

  private static final int LAYOUT_ACTIVITYLOGIN = 11;

  private static final int LAYOUT_ACTIVITYPROFILE = 12;

  private static final int LAYOUT_ACTIVITYRESETPASSWORD = 13;

  private static final int LAYOUT_ACTIVITYSELECTTYPE = 14;

  private static final int LAYOUT_ACTIVITYSTATICPAGE = 15;

  private static final int LAYOUT_ACTIVITYSUBSCRIPTIONS = 16;

  private static final int LAYOUT_ACTIVITYTECHNICALHOME = 17;

  private static final int LAYOUT_ACTIVITYTECHNICALREGISTER = 18;

  private static final int LAYOUT_ACTIVITYTECHNICALSERVICEDETAILS = 19;

  private static final int LAYOUT_ACTIVITYUSERHOME = 20;

  private static final int LAYOUT_ACTIVITYUSERREGISTER = 21;

  private static final int LAYOUT_FRAGMENTTECHNICALPROFILE = 22;

  private static final int LAYOUT_FRAGMENTTECHNICALSERVICES = 23;

  private static final int LAYOUT_FRAGMENTUSERPROFILE = 24;

  private static final int LAYOUT_ITEMAVAILABLITY = 25;

  private static final int LAYOUT_ITEMCERTIFICATE = 26;

  private static final int LAYOUT_ITEMDAY = 27;

  private static final int LAYOUT_ITEMIMAGE = 28;

  private static final int LAYOUT_ITEMPACKAGES = 29;

  private static final int LAYOUT_ITEMSERVICEIMAGE = 30;

  private static final int LAYOUT_ITEMSERVICES = 31;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(31);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_activeuser, LAYOUT_ACTIVITYACTIVEUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_addservice, LAYOUT_ACTIVITYADDSERVICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_appointmentmanagectivity, LAYOUT_ACTIVITYAPPOINTMENTMANAGECTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_changelangue, LAYOUT_ACTIVITYCHANGELANGUE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_contactus, LAYOUT_ACTIVITYCONTACTUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editpass, LAYOUT_ACTIVITYEDITPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editphone, LAYOUT_ACTIVITYEDITPHONE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_edittechnicaldata, LAYOUT_ACTIVITYEDITTECHNICALDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_edituserdata, LAYOUT_ACTIVITYEDITUSERDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_forgetpass, LAYOUT_ACTIVITYFORGETPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_resetpassword, LAYOUT_ACTIVITYRESETPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_selecttype, LAYOUT_ACTIVITYSELECTTYPE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_staticpage, LAYOUT_ACTIVITYSTATICPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_subscriptions, LAYOUT_ACTIVITYSUBSCRIPTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technical_home, LAYOUT_ACTIVITYTECHNICALHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technicalregister, LAYOUT_ACTIVITYTECHNICALREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technicalservicedetails, LAYOUT_ACTIVITYTECHNICALSERVICEDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userhome, LAYOUT_ACTIVITYUSERHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userregister, LAYOUT_ACTIVITYUSERREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalprofile, LAYOUT_FRAGMENTTECHNICALPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalservices, LAYOUT_FRAGMENTTECHNICALSERVICES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_userprofile, LAYOUT_FRAGMENTUSERPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_availablity, LAYOUT_ITEMAVAILABLITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_certificate, LAYOUT_ITEMCERTIFICATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_day, LAYOUT_ITEMDAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_image, LAYOUT_ITEMIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_packages, LAYOUT_ITEMPACKAGES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_serviceimage, LAYOUT_ITEMSERVICEIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_services, LAYOUT_ITEMSERVICES);
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
        case  LAYOUT_ACTIVITYADDSERVICE: {
          if ("layout/activity_addservice_0".equals(tag)) {
            return new AddserviceviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_addservice is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYAPPOINTMENTMANAGECTIVITY: {
          if ("layout/activity_appointmentmanagectivity_0".equals(tag)) {
            return new AppointmentmanageviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_appointmentmanagectivity is invalid. Received: " + tag);
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
        case  LAYOUT_ACTIVITYEDITTECHNICALDATA: {
          if ("layout/activity_edittechnicaldata_0".equals(tag)) {
            return new EdittechnicalViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_edittechnicaldata is invalid. Received: " + tag);
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
        case  LAYOUT_ACTIVITYSUBSCRIPTIONS: {
          if ("layout/activity_subscriptions_0".equals(tag)) {
            return new PlanviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_subscriptions is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYTECHNICALHOME: {
          if ("layout/activity_technical_home_0".equals(tag)) {
            return new TechnicalhomeviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_technical_home is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYTECHNICALREGISTER: {
          if ("layout/activity_technicalregister_0".equals(tag)) {
            return new TechnicalregisterviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_technicalregister is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYTECHNICALSERVICEDETAILS: {
          if ("layout/activity_technicalservicedetails_0".equals(tag)) {
            return new TechnicalservicedetailsviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_technicalservicedetails is invalid. Received: " + tag);
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
        case  LAYOUT_FRAGMENTTECHNICALPROFILE: {
          if ("layout/fragment_technicalprofile_0".equals(tag)) {
            return new TechnicalprofileViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_technicalprofile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTECHNICALSERVICES: {
          if ("layout/fragment_technicalservices_0".equals(tag)) {
            return new TechnicalserviceviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_technicalservices is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTUSERPROFILE: {
          if ("layout/fragment_userprofile_0".equals(tag)) {
            return new UserprofileViewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_userprofile is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMAVAILABLITY: {
          if ("layout/item_availablity_0".equals(tag)) {
            return new AvailbiltyviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_availablity is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCERTIFICATE: {
          if ("layout/item_certificate_0".equals(tag)) {
            return new CertificatviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_certificate is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMDAY: {
          if ("layout/item_day_0".equals(tag)) {
            return new DayviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_day is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMIMAGE: {
          if ("layout/item_image_0".equals(tag)) {
            return new ImagesviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_image is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPACKAGES: {
          if ("layout/item_packages_0".equals(tag)) {
            return new PlanViewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_packages is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSERVICEIMAGE: {
          if ("layout/item_serviceimage_0".equals(tag)) {
            return new TechnicalimagesviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_serviceimage is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSERVICES: {
          if ("layout/item_services_0".equals(tag)) {
            return new ServiceviewModelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_services is invalid. Received: " + tag);
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
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(31);

    static {
      sKeys.put("layout/activity_activeuser_0", fudex.bonyad.R.layout.activity_activeuser);
      sKeys.put("layout/activity_addservice_0", fudex.bonyad.R.layout.activity_addservice);
      sKeys.put("layout/activity_appointmentmanagectivity_0", fudex.bonyad.R.layout.activity_appointmentmanagectivity);
      sKeys.put("layout/activity_changelangue_0", fudex.bonyad.R.layout.activity_changelangue);
      sKeys.put("layout/activity_contactus_0", fudex.bonyad.R.layout.activity_contactus);
      sKeys.put("layout/activity_editpass_0", fudex.bonyad.R.layout.activity_editpass);
      sKeys.put("layout/activity_editphone_0", fudex.bonyad.R.layout.activity_editphone);
      sKeys.put("layout/activity_edittechnicaldata_0", fudex.bonyad.R.layout.activity_edittechnicaldata);
      sKeys.put("layout/activity_edituserdata_0", fudex.bonyad.R.layout.activity_edituserdata);
      sKeys.put("layout/activity_forgetpass_0", fudex.bonyad.R.layout.activity_forgetpass);
      sKeys.put("layout/activity_login_0", fudex.bonyad.R.layout.activity_login);
      sKeys.put("layout/activity_profile_0", fudex.bonyad.R.layout.activity_profile);
      sKeys.put("layout/activity_resetpassword_0", fudex.bonyad.R.layout.activity_resetpassword);
      sKeys.put("layout/activity_selecttype_0", fudex.bonyad.R.layout.activity_selecttype);
      sKeys.put("layout/activity_staticpage_0", fudex.bonyad.R.layout.activity_staticpage);
      sKeys.put("layout/activity_subscriptions_0", fudex.bonyad.R.layout.activity_subscriptions);
      sKeys.put("layout/activity_technical_home_0", fudex.bonyad.R.layout.activity_technical_home);
      sKeys.put("layout/activity_technicalregister_0", fudex.bonyad.R.layout.activity_technicalregister);
      sKeys.put("layout/activity_technicalservicedetails_0", fudex.bonyad.R.layout.activity_technicalservicedetails);
      sKeys.put("layout/activity_userhome_0", fudex.bonyad.R.layout.activity_userhome);
      sKeys.put("layout/activity_userregister_0", fudex.bonyad.R.layout.activity_userregister);
      sKeys.put("layout/fragment_technicalprofile_0", fudex.bonyad.R.layout.fragment_technicalprofile);
      sKeys.put("layout/fragment_technicalservices_0", fudex.bonyad.R.layout.fragment_technicalservices);
      sKeys.put("layout/fragment_userprofile_0", fudex.bonyad.R.layout.fragment_userprofile);
      sKeys.put("layout/item_availablity_0", fudex.bonyad.R.layout.item_availablity);
      sKeys.put("layout/item_certificate_0", fudex.bonyad.R.layout.item_certificate);
      sKeys.put("layout/item_day_0", fudex.bonyad.R.layout.item_day);
      sKeys.put("layout/item_image_0", fudex.bonyad.R.layout.item_image);
      sKeys.put("layout/item_packages_0", fudex.bonyad.R.layout.item_packages);
      sKeys.put("layout/item_serviceimage_0", fudex.bonyad.R.layout.item_serviceimage);
      sKeys.put("layout/item_services_0", fudex.bonyad.R.layout.item_services);
    }
  }
}
