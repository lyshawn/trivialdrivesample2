package com.npplay.testV2.billingtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.niceplay.auth.util.NPGameLog;
import com.niceplay.authclient_three.NPCallBackKeys;
import com.niceplay.authclient_three.NPPlayGameKeys;
import com.niceplay.authclient_three.NPPlayGameSDK;
import com.niceplay.niceplayevent.NPEventConstants;
import com.niceplay.niceplayevent.NicePlayEvent;
import com.niceplay.niceplayfb.NicePlayFB;
import com.niceplay.niceplayfivestarrate.NPRateBuilder;
import com.niceplay.niceplaygb.NicePlayGBillingItem;
import com.niceplay.niceplaygb.NicePlayGBillingV3;
import com.niceplay.niceplaygcm.NicePlayGCMRegister;
import com.niceplay.niceplaymycard.NicePlayMyCardActivity;
import com.niceplay.niceplayonebilling.NicePlayOneStoreV3;
import com.niceplay.niceplaytoollist.NPToolList;
import com.niceplay.niceplaytoollist.NicePlayToolKeys;
import com.niceplay.vip_three.NPVIPDialog;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private LinearLayout LinearLayout1,LinearLayout2,LinearLayout3,LinearLayout4,LinearLayout5,LinearLayout6,LinearLayout7;
    private TextView textview1,textview2,textview3,textview4,textview5,textview10;
    private ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15;
    private EditText editText1,editText2;
    private Spinner spinner1;
    private String appid = "DEMO";
    private String apikey = "daf964f8d22c46d7ce4fb15a555aeece";
    static final int SHORT_DELAY = 2000;

    //Google Billing
    private String SKU_GAS = "android60";                                                        //9splay user id v3//Appid : 9s提供之appid
    private String base64= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAneda+4ZpgTQAIaDhYWaffmyQ3L8zAjWVaocB+D72pXOX34pLl5q9rKQyIHtv2OlnfBoLJpCLQTHutQFLEuwvuvtsN2XOozDd83QM8VNcCRyTn47mb+PpXEL97AQXhyNSZxT0QZ/O8rkuGIAvGrue8MJW9uSDTvLUn1veQl0aoewxH5woY+m9rR2EV7hi/5DuUesrg4BUGK82so6G+TADQJYF1SgqYBkBrH2wWtRpE8DRrFN3BG2Pi/2J0Ns0pEt7ZwmjjuQHhJYdRfphLqT65bj17CxmRBspvWLHcFOvQ0m5fiHeii0Nv6CfPybYX2+EzKzyzjRIgGuMZgaXJ98GyQIDAQAB";
                    //base64EncodedPublicKey
    //private String ProductId= "0910021568";
                    private String GameUID = "";
    private String token = "";
    private String serverid = "server123";                                                          //serverid : 遊戲的serverid
    private String roleid = "role123";                                                              //roleid : 能從遊戲後台能查詢角色的參數,若無此功能請帶入角色的id
    private int bb,cc,dd=0;

    private static final String TAG = "MainActivity" ;
    String[] productArray = new String[]{ "android60" , "android60"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //callbackManager = CallbackManager.Factory.create();

        LinearLayout2 = (LinearLayout) findViewById(R.id.LinearLayout2);
        LinearLayout3 = (LinearLayout) findViewById(R.id.LinearLayout3);
        LinearLayout4 = (LinearLayout) findViewById(R.id.LinearLayout4);
        LinearLayout5 = (LinearLayout) findViewById(R.id.LinearLayout5);
        LinearLayout6 = (LinearLayout) findViewById(R.id.LinearLayout6);
        textview2 = (TextView) findViewById(R.id.textView2);
        textview3 = (TextView) findViewById(R.id.textView3) ;
        textview4 = (TextView) findViewById(R.id.textView4) ;
        textview5 = (TextView) findViewById(R.id.textView5) ;
        textview10 = (TextView) findViewById(R.id.textView10) ;
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1) ;
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2) ;
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3) ;
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        editText1 = (EditText )findViewById(R.id.editText1);
        editText2 = (EditText )findViewById(R.id.editText2);
        spinner1 = (Spinner)findViewById(R.id.spinner1);

        NPGameLog.setDebugMode(true);                                                          //此為Log開關，測試階段請填true

        final NPPlayGameSDK npPlayGameSDK = NPPlayGameSDK.getInstance();

        FacebookSdk.sdkInitialize(getApplicationContext());                                         //facebook 廣告追蹤
        AppEventsLogger.activateApp(this);
        //AppEventsLogger.activateApp(getApplication());                                             //facebook 廣告追蹤

        NicePlayGCMRegister.register(MainActivity.this);                                // P U S H 推播
        NicePlayGCMRegister.ClearNotificationInfo(MainActivity.this);                       //清除 P U S H 推播

        PackageInfo info;                                                                       //抓FB HASH KEY
        try{
            info = getPackageManager().getPackageInfo("com.npplay.testV2.billingtest", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures)
            {   MessageDigest md;
                md =MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String KeyResult =new String(Base64.encode(md.digest(),0));//String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", KeyResult);
                Toast.makeText(this,"My FB Key is \n"+ KeyResult , Toast.LENGTH_LONG ).show();
            }
        }catch(PackageManager.NameNotFoundException e1){Log.e("name not found", e1.toString());
        }catch(NoSuchAlgorithmException e){Log.e("no such an algorithm", e.toString());
        }catch(Exception e){Log.e("exception", e.toString());}


        npPlayGameSDK.settingLanguage(NPPlayGameSDK.ZH_TW);
        npPlayGameSDK.setPlayGameServiceListener(new NPPlayGameSDK.onNPServiceListener() {

            @Override
            public void event(String Name, final int code, final String message, final Bundle b) {

                switch (NPCallBackKeys.valueOf(code)) {
                    //網路連接失敗
                    case NetworkError:
                        break;

                    //玩家拒絕權限
                    case PermissionsDenied:
                        break;

                    //9S登入成功
                    case NPSignInSuccess:

                         GameUID = b.getString(NPPlayGameKeys.NPGameUid.toString());
                         token = b.getString(NPPlayGameKeys.TOKEN.toString());
                         boolean isGooglePlayBinding = b.getBoolean(NPPlayGameKeys.IsGooglePlayBinding.toString());
                         boolean isFacebookBinding = b.getBoolean(NPPlayGameKeys.IsFacebookBinding.toString());

                        textview2.setText("GameUID是  :  "+GameUID + "\n TOKEN 是  :  " + token + "\n 是否綁定GOOGLE PLAY  :  "+isGooglePlayBinding+ "\n 是否綁定Facebook  :  "+isFacebookBinding);
                        break;

                    //GooglePlay登入成功
                    case GooglePlaySignInSuccess:
                        String icon = b.getString(NPPlayGameKeys.ICONIMAGEURL.toString());
                        String name = b.getString(NPPlayGameKeys.DISPLAYNAME.toString());
                        Toast.makeText(MainActivity.this , " 登 入 成 功 \n"+icon+name , Toast.LENGTH_LONG).show();
                        break;

                    //切換帳號成功
                    case SwitchAccountSuccess:
                        GameUID = b.getString(NPPlayGameKeys.NPGameUid.toString());
                        token = b.getString(NPPlayGameKeys.TOKEN.toString());
                         isGooglePlayBinding = b.getBoolean(NPPlayGameKeys.IsGooglePlayBinding.toString());
                         isFacebookBinding = b.getBoolean(NPPlayGameKeys.IsFacebookBinding.toString());
                        textview2.setText("GameUID是  :  "+GameUID + "\n TOKEN 是  :  " + token + "\n 是否綁定GOOGLE PLAY  :  "+isGooglePlayBinding+ "\n 是否綁定Facebook  :  "+isFacebookBinding);
                        break;

                    //切換帳號失敗
                    case SwitchAccountError:
                        break;

                    //帳號轉移成功
                    case AccountTransferSuccess:
                        GameUID = b.getString(NPPlayGameKeys.NPGameUid.toString());
                        token = b.getString(NPPlayGameKeys.TOKEN.toString());
                         isGooglePlayBinding = b.getBoolean(NPPlayGameKeys.IsGooglePlayBinding.toString());
                         isFacebookBinding = b.getBoolean(NPPlayGameKeys.IsFacebookBinding.toString());

                        textview2.setText("GameUID是  :  "+GameUID + "\n TOKEN 是  :  " + token + "\n 是否綁定GOOGLE PLAY  :  "+isGooglePlayBinding+ "\n 是否綁定Facebook  :  "+isFacebookBinding);
                        break;

                    //綁定成功
                    case BindindSuccess:
                        GameUID = b.getString(NPPlayGameKeys.NPGameUid.toString());
                        token = b.getString(NPPlayGameKeys.TOKEN.toString());
                         isGooglePlayBinding = b.getBoolean(NPPlayGameKeys.IsGooglePlayBinding.toString());
                        isFacebookBinding = b.getBoolean(NPPlayGameKeys.IsFacebookBinding.toString());
                        textview2.setText("GameUID是  :  "+GameUID + "\n TOKEN 是  :  " + token + "\n 是否綁定GOOGLE PLAY  :  "+isGooglePlayBinding+ "\n 是否綁定Facebook  :  "+isFacebookBinding);
                        break;
                    default:
                        break;
                }

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {                                           //PUSH推播功能
                        String pushtitle = editText1.getText().toString();
                        String pushmsg = editText2.getText().toString();
                        NicePlayGCMRegister.AddScheduledService(MainActivity.this, SHORT_DELAY, pushmsg,pushtitle);
                        editText1.setText("");
                        editText2.setText("");
                    }
                });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {                           //5星評分視窗
                    @Override
                    public void run() {                                                                        //五星評價
                        new NPRateBuilder(MainActivity.this, NPRateBuilder.Portrait, appid, serverid, roleid)
                                .setFiveStarDialogListener(new NPRateBuilder.OnFiveStarDialogListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onEvent(int Code, int Select, String Message) {
                                        Toast.makeText(MainActivity.this, "Code = " + Code + " , select = " + Select + " , message = " + Message, SHORT_DELAY).show();
                                    }
                                })
                                .create().show();
                    }
                });
            }});

        button3.setOnClickListener(new View.OnClickListener() {                                     //event (0)創角，(1)創角完成
            @Override
            public void onClick(View view) {
                if(bb==0) {
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_ROLE_CREATE_TYPE, "0");
                    logger.logEvent(NPEventConstants.EVENT_NAME_ROLE_CREATE, parameters);
                    String strvalue = parameters.getString(NPEventConstants.EVENT_PARAM_ROLE_CREATE_TYPE, null);
                    textview3.setText(strvalue+" 創角event"); bb++;
                }
                else if(bb==1){
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_ROLE_CREATE_TYPE, "1");
                    logger.logEvent(NPEventConstants.EVENT_NAME_ROLE_CREATE, parameters);
                    String strvaluebb = parameters.getString(NPEventConstants.EVENT_PARAM_ROLE_CREATE_TYPE, null);
                    textview3.setText(strvaluebb +" 創角完成event  ");   bb--; }

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                       //新手、一般任務  &  接取、完成
                if(cc==0){
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, "0");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, "0");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_ID, "Input Mission ID");
                    logger.logEvent(NPEventConstants.EVENT_NAME_MISSION, parameters);
                    String strvaluecc1 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, null);
                    String strvaluecc2 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, null);
                    textview4.setText(strvaluecc1+" "+strvaluecc2+" 新手任務  , 接取"); cc++;
                }
                else if(cc==1){
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, "0");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, "1");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_ID, "Input Mission ID");
                    logger.logEvent(NPEventConstants.EVENT_NAME_MISSION, parameters);
                    String strvaluecc1 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, null);
                    String strvaluecc2 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, null);
                    textview4.setText(strvaluecc1+" "+strvaluecc2+" 新手任務  , 完成"); cc++;
                }
                else if(cc==2){
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, "1");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, "0");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_ID, "Input Mission ID");
                    logger.logEvent(NPEventConstants.EVENT_NAME_MISSION, parameters);
                    String strvaluecc1 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, null);
                    String strvaluecc2 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, null);
                    textview4.setText(strvaluecc1+" "+strvaluecc2+" 一般任務  , 接取"); cc++;
                }
                else if(cc==3){
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, "1");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, "1");
                    parameters.putString(NPEventConstants.EVENT_PARAM_MISSION_ID, "Input Mission ID");
                    logger.logEvent(NPEventConstants.EVENT_NAME_MISSION, parameters);
                    String strvaluecc1 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_TYPE, null);
                    String strvaluecc2 = parameters.getString(NPEventConstants.EVENT_PARAM_MISSION_STATUS, null);
                    textview4.setText(strvaluecc1+" "+strvaluecc2+" 一般任務  , 完成"); cc=0;
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {                                     //等級或經驗值提升
            @Override
            public void onClick(View view) {
                if(dd==0) {
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_LEVEL_TYPE, "0");
                    parameters.putString(NPEventConstants.EVENT_PARAM_LEVEL_AMOUNT, "1");
                    logger.logEvent(NPEventConstants.EVENT_NAME_GET_LEVEL, parameters);
                    String strvaluedd1 = parameters.getString(NPEventConstants.EVENT_PARAM_LEVEL_TYPE, null);
                    String strvaluedd2 = parameters.getString(NPEventConstants.EVENT_PARAM_LEVEL_AMOUNT, null);
                    textview5.setText(strvaluedd1 +" 等級提升 "+ strvaluedd2 +" 級 "); dd++;
                }
                else if(dd==1) {
                    NicePlayEvent logger = new NicePlayEvent(MainActivity.this);
                    Bundle parameters = new Bundle();
                    parameters.putString(NPEventConstants.EVENT_PARAM_LEVEL_TYPE, "1");
                    parameters.putString(NPEventConstants.EVENT_PARAM_LEVEL_AMOUNT, "10");
                    logger.logEvent(NPEventConstants.EVENT_NAME_GET_LEVEL, parameters);
                    String strvaluedd1 = parameters.getString(NPEventConstants.EVENT_PARAM_LEVEL_TYPE, null);
                    String strvaluedd2 = parameters.getString(NPEventConstants.EVENT_PARAM_LEVEL_AMOUNT, null);
                    textview5.setText(strvaluedd1 +" 經驗提升 "+ strvaluedd2); dd=0;
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {                           //FB
                    @Override
                    public void run() {npPlayGameSDK.showNPFBDialog();
                    }
                });
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        npPlayGameSDK.showVIPDialog(MainActivity.this, NPVIPDialog.NP_PORTRAIT_ORIENTATION, new NPVIPDialog.OnVIPBindingListener() {
                            @Override
                            public void onEvent(int i, String codevip) {
                            }
                    });

            }
        });
                button8.setOnClickListener(new View.OnClickListener() {                     // S W I T C H & B I N D
                    @Override
                    public void onClick(View arg0) {

                        npPlayGameSDK.showSwitchAndBindDialog();
                        switch (NPCallBackKeys.valueOf(code)) {
                            //GooglePlay登入成功
                            //切換帳號成功
                            case SwitchAccountSuccess:
                                GameUID = b.getString(NPPlayGameKeys.NPGameUid.toString());
                                token = b.getString(NPPlayGameKeys.TOKEN.toString());
                                boolean isGooglePlayBinding = b.getBoolean(NPPlayGameKeys.IsGooglePlayBinding.toString());
                                boolean isFacebookBinding = b.getBoolean(NPPlayGameKeys.IsFacebookBinding.toString());
                                textview2.setText("GameUID是  :  "+GameUID + "\n TOKEN 是  :  " + token + "\n 是否綁定GOOGLE PLAY  :  "+isGooglePlayBinding+ "\n 是否綁定Facebook  :  "+isFacebookBinding);
                                break;

                            //切換帳號失敗
                            case SwitchAccountError:
                                break;
                        }
                        npPlayGameSDK.refreshToken();

                    }


                });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                npPlayGameSDK.showCustomerService(appid, serverid , roleid ,  NPPlayGameSDK.ZH_TW);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String level = "11";
                npPlayGameSDK.showTransferCustomDialog(GameUID , level);
            }
        });

                /*ArrayAdapter<CharSequence> language = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.language,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(language);*/
         button11.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 npPlayGameSDK.showQuests();
             }
         });
         button12.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                //npPlayGameSDK.goToPlayVideo();
                npPlayGameSDK.showAchievements();

             }
         });
         /*button13.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 npPlayGameSDK.cancelBinding();
                 npPlayGameSDK.refreshToken();
             }
         });
       /*button14.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               NicePlayFB.publishStory(MainActivity.this, new NicePlayFB.OnNicePlayFBPublishStoryOnMeListener() {

                   @Override

                   public void onNicePlayFBPublishStoryOnMeCompletion(Exception error) {

                       if (error != null) {
                           Log.d("niceplayfb", "error: " + error.getMessage());
                       }

                       else {
                           Log.d("niceplayfb", "publish story on me success");
                       }
                   }
               });
           }
       });*/
       button15.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new NPToolList().getWebAccountLogin(MainActivity.this, appid, apikey, new NPToolList.onToolListListener() {

                   @Override
                   public void onEvent(int code, String message, Bundle b) {
                       // TODO Auto-generated method stub

                       switch (code) {
                           case 1:
                               String Account = b.getString(NicePlayToolKeys.Account.toString());
                               String Password = b.getString(NicePlayToolKeys.Password.toString());
                               textview10.setText(code+"\n"+Account +" \n" +Password);
                               break;

                           case -1:
                               Toast.makeText(MainActivity.this , "獲取失敗" , SHORT_DELAY).show();
                               textview10.setText(code+"\n"+"獲取失敗");
                               break;

                           case -11:

                               Toast.makeText(MainActivity.this , "尚未綁定GOOGLE，請綁定GOOGLE。" , SHORT_DELAY).show();
                               textview10.setText(code+"\n"+"尚未綁定GOOGLE，請綁定GOOGLE。");
                               break;

                           default:
                               Toast.makeText(MainActivity.this, "Error", SHORT_DELAY).show();
                               textview10.setText(code+"\n"+"Error");
                               break;
                       }
                   }
               });
           }
       });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                    //GOOGLE 支付
                Intent i = new Intent(MainActivity.this,NicePlayGBillingV3.class);    //Google Billing
                Bundle b = new Bundle();
                b.putString(NicePlayGBillingV3.base64EncodedPublicKey, base64);
                b.putString(NicePlayGBillingV3.ItemID, SKU_GAS);
                b.putString(NicePlayGBillingV3.User_ID_9s,GameUID);                             
                b.putString(NicePlayGBillingV3.Server, serverid); //要儲值的目標 Server，僅可使用英文與數字
                //b.putString(NicePlayGBillingV3.Country, Country);
                b.putBoolean(NicePlayGBillingV3.DebugMode, false);  // 此功能是為了debug用，會顯示出log，如要出正式版本可斟酌是否設為false，預設值為false
                b.putString(NicePlayGBillingV3.APPID, appid);
                b.putString(NicePlayGBillingV3.Role, roleid); //要儲值的角色 ID，優先使用英文與數字
                b.putString(NicePlayGBillingV3.Order, "TestItem1"); //遊戲端訂單資訊，支付完成會一併回傳特殊字串_*#@是可以用的
                i.putExtras(b);
                startActivityForResult(i, NicePlayGBillingV3.GBilling_REQUEST);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                            //MyCard支付 (小額付費儲值
                Intent i = new Intent(MainActivity.this, NicePlayMyCardActivity.class);
                Bundle b = new Bundle();
                b.putString("type", NicePlayMyCardActivity.TYPE_SMALL_PAYMENT);
                        b.putString("uid",GameUID);
                                b.putString(com.niceplay.niceplaymycard.NicePlayKeys.ApiKey.toString(), "ApiKey from 9splay");//請寫入由9SPLAY提供之APIKEY
                                        b.putString(com.niceplay.niceplaymycard.NicePlayKeys.AppID.toString(), "DEMO");//請寫入由9SPLAY提供之APPID
                                                b.putString(com.niceplay.niceplaymycard.NicePlayKeys.Country.toString(),NicePlayMyCardActivity.TW);
                i.putExtras(b);
                startActivityForResult(i,100);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                //MyCard支付 (序號儲值
                Intent i = new Intent(MainActivity.this, NicePlayMyCardActivity.class);
                Bundle b = new Bundle();
                b.putString("type", NicePlayMyCardActivity.TYPE_SERIAL_NUMBER); //儲值類型
                        b.putString(com.niceplay.niceplaymycard.NicePlayKeys.ApiKey.toString(), "ApiKey from 9splay"); //請寫入由9SPLAY提供之APIKEY
                                b.putString(com.niceplay.niceplaymycard.NicePlayKeys.AppID.toString(), "DEMO");//請寫入由9SPLAY提供之APPID
                                        b.putString(com.niceplay.niceplaymycard.NicePlayKeys.Country.toString(),NicePlayMyCardActivity.TW);
                b.putString("uid",GameUID);//玩家uid
                i.putExtras(b);
                startActivityForResult(i,100);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,NicePlayOneStoreV3.class);
                Bundle b = new Bundle();
                b.putString(NicePlayOneStoreV3.User_ID_9s,GameUID);
                b.putString(NicePlayOneStoreV3.OneStoreAPPID, appid);
                b.putString(NicePlayOneStoreV3.ItemID, "0910021568");
                b.putString(NicePlayOneStoreV3.ItemName, "123");
                b.putString(NicePlayOneStoreV3.ItemPrice, "10");
                b.putString(NicePlayOneStoreV3.PluginMode,"development");  // “development"    or     “release"
                b.putString(NicePlayOneStoreV3.NicePlayAppid,"DEMO" );
                b.putBoolean(NicePlayOneStoreV3.DebugMode,false);
                i.putExtras(b);
                startActivityForResult(i, 100);
            }
        });

            }


        });
        new NicePlayGBillingItem().getGBillingData(MainActivity.this, productArray, base64, new NicePlayGBillingItem.OnGBillingItemListener() {
            @Override
            public void onQueryFinished(int code, String productJson, String message) {
                Log.d(TAG, "Data = " + productJson + " , Message = " + message);
            }
        });

        npPlayGameSDK.setLoginRequestCode(888);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //NicePlayFB.onActivityResult(MainActivity.this,requestCode,resultCode,data);


        //callbackManager.onActivityResult(requestCode,resultCode, data);
        if(requestCode == 888){
            NPPlayGameSDK.getInstance().onActivityResult(requestCode, resultCode, data);
        }



        if(requestCode == NicePlayGBillingV3.GBilling_REQUEST) {
            int code = data.getExtras().getInt(NicePlayGBillingV3.code);
            String message = data.getExtras().getString(NicePlayGBillingV3.message);
            //當code = 1時代表成功，此時才會有tradeid與bundle的參數，如果是其它的code，在message部分會說明為何
            String tradeid = data.getExtras().getString(NicePlayGBillingV3.Tradeid);
            textview10.setText(code + "\n" + message + "\n" + tradeid);
            Bundle b = data.getExtras().getBundle("Data");//此為傳入給google支付時候bundle
        }
        NicePlayGCMRegister.ClearNotificationInfo(MainActivity.this);
    }


}