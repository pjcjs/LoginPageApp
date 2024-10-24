package LoginPageApp;

import java.util.*;

//3-adım:userla ilgili metodlar
public class UserService {

    public Scanner scanner=new Scanner(System.in);

    //List<User> users=new ArrayList<>();-->practice

    public Map<String,String> userInfos=new HashMap<>();// K:email V:password

    //4-adım:kullanıcıdan alınan ad-soyad,email,password bilgileriyle üye olma metodu
    public void register(){
        System.out.println("Ad-Soyad giriniz : ");
        String name=scanner.nextLine();

        //email geçersizse tekrar girilmeli
        String email;
        boolean isValid;//T:geçerli F:geçerli değil
        do {
            System.out.println("Email giriniz : ");
            email=scanner.nextLine();//asd@gmail.com

            //email geçerli mi
            isValid=validateEmail(email);//T

            //email unique olmalı:bu email ile kayıtlı kullanıcı var mı?
            boolean isExistEmail=this.userInfos.containsKey(email);//T

            if (isExistEmail){//T
                System.out.println("Bu email ile kayıtlı kullanıcı zaten var! ");
                isValid=false;
            }
        }while (!isValid);

        //geçerli bir password oluşturma
        String password;
        boolean isValidPassword;

        do {
            System.out.println("Parolayı oluşturunuz : ");
            password=scanner.nextLine();

            //geçerli mi
            isValidPassword=validatePassword(password);

        }while (!isValidPassword);

        //user oluşturalım
        User user=new User(name,email,password);

        //email ve password bilgisini userInfos a ekleyelim.
        this.userInfos.put(user.getEmail(), user.getPassword());

        System.out.println("Sayın "+user.getName()+", tebrikler, kayıt işlemi başarıyla tamamlandı. ");
        System.out.println("Email ve şifrenizi kullanarak sisteme giriş yapabilirsiniz.");

    }

    //7-adım:giriş yapma metodu
    public void login(){

        System.out.println("Email giriniz : ");
        String email=scanner.nextLine();

        //kullanıcı kaydı var mı
        if (this.userInfos.containsKey(email)){//kullanıcı var
            //şifre soralım

            boolean isSuccess=false;//false iken döngü devam etmeli

            int counter=3;

            while (!isSuccess && counter>0){

                System.out.println("Şifrenizi giriniz : ");
                String password=scanner.nextLine();

                //email ile password eşleşiyor mu
                if (this.userInfos.get(email).equals(password)){//şifre doğru
                    //giriş başarılı
                    System.out.println("Harika, sisteme giriş yaptınız. Hoşgeldiniz:)");
                    isSuccess=true;

                }else{
                    //şifre hatalı
                    counter--;//2-1-0
                    if (counter==0){
                        System.out.println("3 kez hatalı giriş yaptınız! Ana menüye yönlendiriliyorsunuz!");
                    }else {
                        System.out.println("Şifreniz yanlış, tekrar deneyiniz, kalan hak: "+counter);
                    }
                }
            }

        }else {//kullanıcı yok
            System.out.println("Sisteme kayıtlı kullanıcı bulunamadı!");
            System.out.println("Üyeyseniz bilgileriniz kontrol ediniz, değilseniz lütfen üye olunuz.");
        }
    }



    //5-adım:email doğrulama:ÖDEVV
    private boolean validateEmail(String email){
        boolean isValid=true;

        boolean hasSpace=email.contains(" ");
        boolean hasAtSymbol=email.contains("@");

        if (hasSpace){
            System.out.println("Email boşluk içeremez!");
            isValid=false;
        } else if (!hasAtSymbol) {
            System.out.println("Email @ sembolünü içermelidir!");
            isValid=false;
        }else{

            String firstPart=email.split("@")[0];
            String secondPart=email.split("@")[1];

            boolean isExistInvalidChar=firstPart.replaceAll("[A-Za-z0-9-._]","").length()>0;
            //* ? /

            boolean isValidDomain=secondPart.equals("gmail.com") ||
                                  secondPart.equals("hotmail.com") ||
                                  secondPart.equals("yahoo.com");

            if (isExistInvalidChar){
                System.out.println("Email kullanıcı adı büyük-küçük harf, rakam, -._ dışında karakter içeremez!");
                isValid=false;
            }else if (!isValidDomain){
                System.out.println("Sisteme sadece gmail,yahoo ve hotmail ile kayıt olabilirsiniz!");
                isValid=false;
            }


        }

        if (!isValid){
            System.out.println("Geçersiz email, tekrar deneyiniz! ");
        }

        return isValid;
    }

    //6-adım:password doğrulama:ÖDEVV
    private boolean validatePassword(String password){
        return true;
    }



}
