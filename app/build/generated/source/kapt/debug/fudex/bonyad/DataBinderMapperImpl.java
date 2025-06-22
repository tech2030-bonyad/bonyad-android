package fudex.bonyad;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import fudex.bonyad.databinding.ActivecodeviewModelBindingImpl;
import fudex.bonyad.databinding.AddressesListViewModelBindingImpl;
import fudex.bonyad.databinding.AddressesiewModelBindingImpl;
import fudex.bonyad.databinding.AddserviceviewModelBindingImpl;
import fudex.bonyad.databinding.AppointmentmanageviewModelBindingImpl;
import fudex.bonyad.databinding.AvailbiltyviewModelBindingImpl;
import fudex.bonyad.databinding.CalenderViewModelBindingImpl;
import fudex.bonyad.databinding.CertificatviewModelBindingImpl;
import fudex.bonyad.databinding.ChangelanguageviewModelBindingImpl;
import fudex.bonyad.databinding.ChatListViewModelBindingImpl;
import fudex.bonyad.databinding.ChatviewModelBindingImpl;
import fudex.bonyad.databinding.ContactusviewModelBindingImpl;
import fudex.bonyad.databinding.DayviewModelBindingImpl;
import fudex.bonyad.databinding.DeleteviewModelBindingImpl;
import fudex.bonyad.databinding.DetailappointmentsviewModelBindingImpl;
import fudex.bonyad.databinding.DetailstechnicalModelBindingImpl;
import fudex.bonyad.databinding.EditpasswordViewModelBindingImpl;
import fudex.bonyad.databinding.EditphoneViewModelBindingImpl;
import fudex.bonyad.databinding.EdittechnicalViewModelBindingImpl;
import fudex.bonyad.databinding.EdituserViewModelBindingImpl;
import fudex.bonyad.databinding.FiltertechnicalViewModelBindingImpl;
import fudex.bonyad.databinding.ForgetviewModelBindingImpl;
import fudex.bonyad.databinding.ImagesviewModelBindingImpl;
import fudex.bonyad.databinding.LocateonmapviewModelBindingImpl;
import fudex.bonyad.databinding.LoginviewModelBindingImpl;
import fudex.bonyad.databinding.MyerviceviewModelBindingImpl;
import fudex.bonyad.databinding.OnboardingListViewModelBindingImpl;
import fudex.bonyad.databinding.PlanViewBindingImpl;
import fudex.bonyad.databinding.PlanviewModelBindingImpl;
import fudex.bonyad.databinding.ProfileviewModelBindingImpl;
import fudex.bonyad.databinding.RatingViewModelBindingImpl;
import fudex.bonyad.databinding.Refuse1ViewModelBindingImpl;
import fudex.bonyad.databinding.RefuseViewModelBindingImpl;
import fudex.bonyad.databinding.ReservedoneviewModelBindingImpl;
import fudex.bonyad.databinding.ResetpassviewModelBindingImpl;
import fudex.bonyad.databinding.SelecttypeviewModelBindingImpl;
import fudex.bonyad.databinding.Service1viewModelBindingImpl;
import fudex.bonyad.databinding.ServicedetailsviewModelBindingImpl;
import fudex.bonyad.databinding.ServiceviewModelBindingImpl;
import fudex.bonyad.databinding.StaticpageviewModelBindingImpl;
import fudex.bonyad.databinding.Technicail1viewModelBindingImpl;
import fudex.bonyad.databinding.TechnicailservicesviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicailviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalHomeorderviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalListviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalappointmentdetailsviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalhomefragmentViewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalhomeviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalimagesviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalordersfragmentviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalorderviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalprofileViewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalregisterviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalservicedetailsviewModelBindingImpl;
import fudex.bonyad.databinding.TechnicalserviceviewModelBindingImpl;
import fudex.bonyad.databinding.TimeviewModelBindingImpl;
import fudex.bonyad.databinding.UserhomefragmentviewModelBindingImpl;
import fudex.bonyad.databinding.UserhomeviewModelBindingImpl;
import fudex.bonyad.databinding.UserordersfragmentviewModelBindingImpl;
import fudex.bonyad.databinding.UserorderviewModelBindingImpl;
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

  private static final int LAYOUT_ACTIVITYADDRESSES = 2;

  private static final int LAYOUT_ACTIVITYADDSERVICE = 3;

  private static final int LAYOUT_ACTIVITYAPPOINTMENTMANAGECTIVITY = 4;

  private static final int LAYOUT_ACTIVITYCHANGELANGUE = 5;

  private static final int LAYOUT_ACTIVITYCHAT = 6;

  private static final int LAYOUT_ACTIVITYCONTACTUS = 7;

  private static final int LAYOUT_ACTIVITYDETAILSAPPOINTMENT = 8;

  private static final int LAYOUT_ACTIVITYDETAILSSPECIALLIST = 9;

  private static final int LAYOUT_ACTIVITYEDITPASS = 10;

  private static final int LAYOUT_ACTIVITYEDITPHONE = 11;

  private static final int LAYOUT_ACTIVITYEDITTECHNICALDATA = 12;

  private static final int LAYOUT_ACTIVITYEDITUSERDATA = 13;

  private static final int LAYOUT_ACTIVITYFORGETPASS = 14;

  private static final int LAYOUT_ACTIVITYLOCATIONMAP = 15;

  private static final int LAYOUT_ACTIVITYLOGIN = 16;

  private static final int LAYOUT_ACTIVITYMYSERVICES = 17;

  private static final int LAYOUT_ACTIVITYONBOARDING = 18;

  private static final int LAYOUT_ACTIVITYPROFILE = 19;

  private static final int LAYOUT_ACTIVITYRESETPASSWORD = 20;

  private static final int LAYOUT_ACTIVITYSELECTTYPE = 21;

  private static final int LAYOUT_ACTIVITYSPECIALISTS = 22;

  private static final int LAYOUT_ACTIVITYSTATICPAGE = 23;

  private static final int LAYOUT_ACTIVITYSUBSCRIPTIONS = 24;

  private static final int LAYOUT_ACTIVITYTECHNICALHOME = 25;

  private static final int LAYOUT_ACTIVITYTECHNICALDETAILSAPOINTMENT = 26;

  private static final int LAYOUT_ACTIVITYTECHNICALREGISTER = 27;

  private static final int LAYOUT_ACTIVITYTECHNICALSERVICEDETAILS = 28;

  private static final int LAYOUT_ACTIVITYUSERHOME = 29;

  private static final int LAYOUT_ACTIVITYUSERREGISTER = 30;

  private static final int LAYOUT_FRAGMENTCALENDERDIALOG = 31;

  private static final int LAYOUT_FRAGMENTDELETE = 32;

  private static final int LAYOUT_FRAGMENTFILTERSPECIAL = 33;

  private static final int LAYOUT_FRAGMENTRATINGDIALOG = 34;

  private static final int LAYOUT_FRAGMENTREFUSE = 35;

  private static final int LAYOUT_FRAGMENTREFUSE1 = 36;

  private static final int LAYOUT_FRAGMENTSPECIALLISTRESERVEDONE = 37;

  private static final int LAYOUT_FRAGMENTTECHNICALAPPOINTMENT = 38;

  private static final int LAYOUT_FRAGMENTTECHNICALHOME = 39;

  private static final int LAYOUT_FRAGMENTTECHNICALPROFILE = 40;

  private static final int LAYOUT_FRAGMENTTECHNICALSERVICES = 41;

  private static final int LAYOUT_FRAGMENTUSERAPPOINTMENT = 42;

  private static final int LAYOUT_FRAGMENTUSERHOME = 43;

  private static final int LAYOUT_FRAGMENTUSERPROFILE = 44;

  private static final int LAYOUT_ITEMADDRESS = 45;

  private static final int LAYOUT_ITEMAVAILABLITY = 46;

  private static final int LAYOUT_ITEMCERTIFICATE = 47;

  private static final int LAYOUT_ITEMCHAT = 48;

  private static final int LAYOUT_ITEMDAY = 49;

  private static final int LAYOUT_ITEMIMAGE = 50;

  private static final int LAYOUT_ITEMORDERS = 51;

  private static final int LAYOUT_ITEMPACKAGES = 52;

  private static final int LAYOUT_ITEMSERVICEIMAGE = 53;

  private static final int LAYOUT_ITEMSERVICES = 54;

  private static final int LAYOUT_ITEMSERVICES1 = 55;

  private static final int LAYOUT_ITEMSERVICESDETAILS = 56;

  private static final int LAYOUT_ITEMSPECIAL = 57;

  private static final int LAYOUT_ITEMSPECIAL1 = 58;

  private static final int LAYOUT_ITEMTECHNICALAPPOINTMENT = 59;

  private static final int LAYOUT_ITEMTECHNICALSERVICE = 60;

  private static final int LAYOUT_ITEMTIME = 61;

  private static final int LAYOUT_ITEMUSERAPPOINTMENT = 62;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(62);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_activeuser, LAYOUT_ACTIVITYACTIVEUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_addresses, LAYOUT_ACTIVITYADDRESSES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_addservice, LAYOUT_ACTIVITYADDSERVICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_appointmentmanagectivity, LAYOUT_ACTIVITYAPPOINTMENTMANAGECTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_changelangue, LAYOUT_ACTIVITYCHANGELANGUE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_chat, LAYOUT_ACTIVITYCHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_contactus, LAYOUT_ACTIVITYCONTACTUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_detailsappointment, LAYOUT_ACTIVITYDETAILSAPPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_detailsspeciallist, LAYOUT_ACTIVITYDETAILSSPECIALLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editpass, LAYOUT_ACTIVITYEDITPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_editphone, LAYOUT_ACTIVITYEDITPHONE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_edittechnicaldata, LAYOUT_ACTIVITYEDITTECHNICALDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_edituserdata, LAYOUT_ACTIVITYEDITUSERDATA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_forgetpass, LAYOUT_ACTIVITYFORGETPASS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_locationmap, LAYOUT_ACTIVITYLOCATIONMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_myservices, LAYOUT_ACTIVITYMYSERVICES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_onboarding, LAYOUT_ACTIVITYONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_resetpassword, LAYOUT_ACTIVITYRESETPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_selecttype, LAYOUT_ACTIVITYSELECTTYPE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_specialists, LAYOUT_ACTIVITYSPECIALISTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_staticpage, LAYOUT_ACTIVITYSTATICPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_subscriptions, LAYOUT_ACTIVITYSUBSCRIPTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technical_home, LAYOUT_ACTIVITYTECHNICALHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technicaldetailsapointment, LAYOUT_ACTIVITYTECHNICALDETAILSAPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technicalregister, LAYOUT_ACTIVITYTECHNICALREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_technicalservicedetails, LAYOUT_ACTIVITYTECHNICALSERVICEDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userhome, LAYOUT_ACTIVITYUSERHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.activity_userregister, LAYOUT_ACTIVITYUSERREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_calenderdialog, LAYOUT_FRAGMENTCALENDERDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_delete, LAYOUT_FRAGMENTDELETE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_filterspecial, LAYOUT_FRAGMENTFILTERSPECIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_ratingdialog, LAYOUT_FRAGMENTRATINGDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_refuse, LAYOUT_FRAGMENTREFUSE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_refuse1, LAYOUT_FRAGMENTREFUSE1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_speciallistreservedone, LAYOUT_FRAGMENTSPECIALLISTRESERVEDONE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalappointment, LAYOUT_FRAGMENTTECHNICALAPPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalhome, LAYOUT_FRAGMENTTECHNICALHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalprofile, LAYOUT_FRAGMENTTECHNICALPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_technicalservices, LAYOUT_FRAGMENTTECHNICALSERVICES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_userappointment, LAYOUT_FRAGMENTUSERAPPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_userhome, LAYOUT_FRAGMENTUSERHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.fragment_userprofile, LAYOUT_FRAGMENTUSERPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_address, LAYOUT_ITEMADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_availablity, LAYOUT_ITEMAVAILABLITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_certificate, LAYOUT_ITEMCERTIFICATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_chat, LAYOUT_ITEMCHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_day, LAYOUT_ITEMDAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_image, LAYOUT_ITEMIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_orders, LAYOUT_ITEMORDERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_packages, LAYOUT_ITEMPACKAGES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_serviceimage, LAYOUT_ITEMSERVICEIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_services, LAYOUT_ITEMSERVICES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_services1, LAYOUT_ITEMSERVICES1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_servicesdetails, LAYOUT_ITEMSERVICESDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_special, LAYOUT_ITEMSPECIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_special1, LAYOUT_ITEMSPECIAL1);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_technicalappointment, LAYOUT_ITEMTECHNICALAPPOINTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_technicalservice, LAYOUT_ITEMTECHNICALSERVICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_time, LAYOUT_ITEMTIME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(fudex.bonyad.R.layout.item_userappointment, LAYOUT_ITEMUSERAPPOINTMENT);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYACTIVEUSER: {
        if ("layout/activity_activeuser_0".equals(tag)) {
          return new ActivecodeviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_activeuser is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDRESSES: {
        if ("layout/activity_addresses_0".equals(tag)) {
          return new AddressesListViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_addresses is invalid. Received: " + tag);
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
      case  LAYOUT_ACTIVITYCHAT: {
        if ("layout/activity_chat_0".equals(tag)) {
          return new ChatListViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_chat is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCONTACTUS: {
        if ("layout/activity_contactus_0".equals(tag)) {
          return new ContactusviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_contactus is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYDETAILSAPPOINTMENT: {
        if ("layout/activity_detailsappointment_0".equals(tag)) {
          return new DetailappointmentsviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_detailsappointment is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYDETAILSSPECIALLIST: {
        if ("layout/activity_detailsspeciallist_0".equals(tag)) {
          return new DetailstechnicalModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_detailsspeciallist is invalid. Received: " + tag);
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
      case  LAYOUT_ACTIVITYLOCATIONMAP: {
        if ("layout/activity_locationmap_0".equals(tag)) {
          return new LocateonmapviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_locationmap is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLOGIN: {
        if ("layout/activity_login_0".equals(tag)) {
          return new LoginviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMYSERVICES: {
        if ("layout/activity_myservices_0".equals(tag)) {
          return new MyerviceviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_myservices is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYONBOARDING: {
        if ("layout/activity_onboarding_0".equals(tag)) {
          return new OnboardingListViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_onboarding is invalid. Received: " + tag);
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
      case  LAYOUT_ACTIVITYSPECIALISTS: {
        if ("layout/activity_specialists_0".equals(tag)) {
          return new TechnicalListviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_specialists is invalid. Received: " + tag);
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
      case  LAYOUT_ACTIVITYTECHNICALDETAILSAPOINTMENT: {
        if ("layout/activity_technicaldetailsapointment_0".equals(tag)) {
          return new TechnicalappointmentdetailsviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_technicaldetailsapointment is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTCALENDERDIALOG: {
        if ("layout/fragment_calenderdialog_0".equals(tag)) {
          return new CalenderViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_calenderdialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTDELETE: {
        if ("layout/fragment_delete_0".equals(tag)) {
          return new DeleteviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_delete is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTFILTERSPECIAL: {
        if ("layout/fragment_filterspecial_0".equals(tag)) {
          return new FiltertechnicalViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_filterspecial is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTRATINGDIALOG: {
        if ("layout/fragment_ratingdialog_0".equals(tag)) {
          return new RatingViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_ratingdialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREFUSE: {
        if ("layout/fragment_refuse_0".equals(tag)) {
          return new RefuseViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_refuse is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREFUSE1: {
        if ("layout/fragment_refuse1_0".equals(tag)) {
          return new Refuse1ViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_refuse1 is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTSPECIALLISTRESERVEDONE: {
        if ("layout/fragment_speciallistreservedone_0".equals(tag)) {
          return new ReservedoneviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_speciallistreservedone is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTTECHNICALAPPOINTMENT: {
        if ("layout/fragment_technicalappointment_0".equals(tag)) {
          return new TechnicalordersfragmentviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_technicalappointment is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTTECHNICALHOME: {
        if ("layout/fragment_technicalhome_0".equals(tag)) {
          return new TechnicalhomefragmentViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_technicalhome is invalid. Received: " + tag);
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
      case  LAYOUT_FRAGMENTUSERAPPOINTMENT: {
        if ("layout/fragment_userappointment_0".equals(tag)) {
          return new UserordersfragmentviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_userappointment is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTUSERHOME: {
        if ("layout/fragment_userhome_0".equals(tag)) {
          return new UserhomefragmentviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_userhome is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTUSERPROFILE: {
        if ("layout/fragment_userprofile_0".equals(tag)) {
          return new UserprofileViewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_userprofile is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMADDRESS: {
        if ("layout/item_address_0".equals(tag)) {
          return new AddressesiewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_address is invalid. Received: " + tag);
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
      case  LAYOUT_ITEMCHAT: {
        if ("layout/item_chat_0".equals(tag)) {
          return new ChatviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_chat is invalid. Received: " + tag);
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
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ITEMORDERS: {
        if ("layout/item_orders_0".equals(tag)) {
          return new TechnicalHomeorderviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_orders is invalid. Received: " + tag);
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
      case  LAYOUT_ITEMSERVICES1: {
        if ("layout/item_services1_0".equals(tag)) {
          return new Service1viewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_services1 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMSERVICESDETAILS: {
        if ("layout/item_servicesdetails_0".equals(tag)) {
          return new ServicedetailsviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_servicesdetails is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMSPECIAL: {
        if ("layout/item_special_0".equals(tag)) {
          return new TechnicailviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_special is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMSPECIAL1: {
        if ("layout/item_special1_0".equals(tag)) {
          return new Technicail1viewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_special1 is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTECHNICALAPPOINTMENT: {
        if ("layout/item_technicalappointment_0".equals(tag)) {
          return new TechnicalorderviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_technicalappointment is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTECHNICALSERVICE: {
        if ("layout/item_technicalservice_0".equals(tag)) {
          return new TechnicailservicesviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_technicalservice is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTIME: {
        if ("layout/item_time_0".equals(tag)) {
          return new TimeviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_time is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMUSERAPPOINTMENT: {
        if ("layout/item_userappointment_0".equals(tag)) {
          return new UserorderviewModelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_userappointment is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
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
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(62);

    static {
      sKeys.put("layout/activity_activeuser_0", fudex.bonyad.R.layout.activity_activeuser);
      sKeys.put("layout/activity_addresses_0", fudex.bonyad.R.layout.activity_addresses);
      sKeys.put("layout/activity_addservice_0", fudex.bonyad.R.layout.activity_addservice);
      sKeys.put("layout/activity_appointmentmanagectivity_0", fudex.bonyad.R.layout.activity_appointmentmanagectivity);
      sKeys.put("layout/activity_changelangue_0", fudex.bonyad.R.layout.activity_changelangue);
      sKeys.put("layout/activity_chat_0", fudex.bonyad.R.layout.activity_chat);
      sKeys.put("layout/activity_contactus_0", fudex.bonyad.R.layout.activity_contactus);
      sKeys.put("layout/activity_detailsappointment_0", fudex.bonyad.R.layout.activity_detailsappointment);
      sKeys.put("layout/activity_detailsspeciallist_0", fudex.bonyad.R.layout.activity_detailsspeciallist);
      sKeys.put("layout/activity_editpass_0", fudex.bonyad.R.layout.activity_editpass);
      sKeys.put("layout/activity_editphone_0", fudex.bonyad.R.layout.activity_editphone);
      sKeys.put("layout/activity_edittechnicaldata_0", fudex.bonyad.R.layout.activity_edittechnicaldata);
      sKeys.put("layout/activity_edituserdata_0", fudex.bonyad.R.layout.activity_edituserdata);
      sKeys.put("layout/activity_forgetpass_0", fudex.bonyad.R.layout.activity_forgetpass);
      sKeys.put("layout/activity_locationmap_0", fudex.bonyad.R.layout.activity_locationmap);
      sKeys.put("layout/activity_login_0", fudex.bonyad.R.layout.activity_login);
      sKeys.put("layout/activity_myservices_0", fudex.bonyad.R.layout.activity_myservices);
      sKeys.put("layout/activity_onboarding_0", fudex.bonyad.R.layout.activity_onboarding);
      sKeys.put("layout/activity_profile_0", fudex.bonyad.R.layout.activity_profile);
      sKeys.put("layout/activity_resetpassword_0", fudex.bonyad.R.layout.activity_resetpassword);
      sKeys.put("layout/activity_selecttype_0", fudex.bonyad.R.layout.activity_selecttype);
      sKeys.put("layout/activity_specialists_0", fudex.bonyad.R.layout.activity_specialists);
      sKeys.put("layout/activity_staticpage_0", fudex.bonyad.R.layout.activity_staticpage);
      sKeys.put("layout/activity_subscriptions_0", fudex.bonyad.R.layout.activity_subscriptions);
      sKeys.put("layout/activity_technical_home_0", fudex.bonyad.R.layout.activity_technical_home);
      sKeys.put("layout/activity_technicaldetailsapointment_0", fudex.bonyad.R.layout.activity_technicaldetailsapointment);
      sKeys.put("layout/activity_technicalregister_0", fudex.bonyad.R.layout.activity_technicalregister);
      sKeys.put("layout/activity_technicalservicedetails_0", fudex.bonyad.R.layout.activity_technicalservicedetails);
      sKeys.put("layout/activity_userhome_0", fudex.bonyad.R.layout.activity_userhome);
      sKeys.put("layout/activity_userregister_0", fudex.bonyad.R.layout.activity_userregister);
      sKeys.put("layout/fragment_calenderdialog_0", fudex.bonyad.R.layout.fragment_calenderdialog);
      sKeys.put("layout/fragment_delete_0", fudex.bonyad.R.layout.fragment_delete);
      sKeys.put("layout/fragment_filterspecial_0", fudex.bonyad.R.layout.fragment_filterspecial);
      sKeys.put("layout/fragment_ratingdialog_0", fudex.bonyad.R.layout.fragment_ratingdialog);
      sKeys.put("layout/fragment_refuse_0", fudex.bonyad.R.layout.fragment_refuse);
      sKeys.put("layout/fragment_refuse1_0", fudex.bonyad.R.layout.fragment_refuse1);
      sKeys.put("layout/fragment_speciallistreservedone_0", fudex.bonyad.R.layout.fragment_speciallistreservedone);
      sKeys.put("layout/fragment_technicalappointment_0", fudex.bonyad.R.layout.fragment_technicalappointment);
      sKeys.put("layout/fragment_technicalhome_0", fudex.bonyad.R.layout.fragment_technicalhome);
      sKeys.put("layout/fragment_technicalprofile_0", fudex.bonyad.R.layout.fragment_technicalprofile);
      sKeys.put("layout/fragment_technicalservices_0", fudex.bonyad.R.layout.fragment_technicalservices);
      sKeys.put("layout/fragment_userappointment_0", fudex.bonyad.R.layout.fragment_userappointment);
      sKeys.put("layout/fragment_userhome_0", fudex.bonyad.R.layout.fragment_userhome);
      sKeys.put("layout/fragment_userprofile_0", fudex.bonyad.R.layout.fragment_userprofile);
      sKeys.put("layout/item_address_0", fudex.bonyad.R.layout.item_address);
      sKeys.put("layout/item_availablity_0", fudex.bonyad.R.layout.item_availablity);
      sKeys.put("layout/item_certificate_0", fudex.bonyad.R.layout.item_certificate);
      sKeys.put("layout/item_chat_0", fudex.bonyad.R.layout.item_chat);
      sKeys.put("layout/item_day_0", fudex.bonyad.R.layout.item_day);
      sKeys.put("layout/item_image_0", fudex.bonyad.R.layout.item_image);
      sKeys.put("layout/item_orders_0", fudex.bonyad.R.layout.item_orders);
      sKeys.put("layout/item_packages_0", fudex.bonyad.R.layout.item_packages);
      sKeys.put("layout/item_serviceimage_0", fudex.bonyad.R.layout.item_serviceimage);
      sKeys.put("layout/item_services_0", fudex.bonyad.R.layout.item_services);
      sKeys.put("layout/item_services1_0", fudex.bonyad.R.layout.item_services1);
      sKeys.put("layout/item_servicesdetails_0", fudex.bonyad.R.layout.item_servicesdetails);
      sKeys.put("layout/item_special_0", fudex.bonyad.R.layout.item_special);
      sKeys.put("layout/item_special1_0", fudex.bonyad.R.layout.item_special1);
      sKeys.put("layout/item_technicalappointment_0", fudex.bonyad.R.layout.item_technicalappointment);
      sKeys.put("layout/item_technicalservice_0", fudex.bonyad.R.layout.item_technicalservice);
      sKeys.put("layout/item_time_0", fudex.bonyad.R.layout.item_time);
      sKeys.put("layout/item_userappointment_0", fudex.bonyad.R.layout.item_userappointment);
    }
  }
}
