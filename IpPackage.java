class IpPackage {

    private int var1;
    private int var2;
    private int var3;
    private int var4;

    //Accesseur des différents octets.
    public int getOctet1() {
        return this.var1;
    }
    public int getOctet2() {
        return this.var2;
    }
    public int getOctet3() {
        return this.var3;
    }
    public int getOctet4() {
        return this.var4;
    }
    
    //Constructeur qui permet d'instancier l'objet en prenant 4 octets en paramètre.
    private IpPackage(int var1, int var2, int var3, int var4){
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    //Méthode qui retourne un objet qui vérifie la validité de l'adresse IP.
    public static IpPackage getInstance(int var1, int var2, int var3, int var4){

        try {
            if(var1 < 0 || var1 >255 || var2 < 0 || var2 >255 || var3 < 0 || var3 >255 || var4 < 0 || var4 >255) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

        System.out.println("Octet 1 = " + var1);
        System.out.println("Octet 2 = " + var2);
        System.out.println("Octet 3 = " + var3);
        System.out.println("Octet 4 = " + var4);

        return new IpPackage(var1, var2, var3, var4);
    }

    //Méthode qui retourne un caractère A, B ou C qui identifie la classe de l'adresse IP.
    public String getClasse(){
        String lettre = " ";
        if (this.var1 <= 126) {
            lettre = "A";
        }
        if (this.var1 >= 126 && this.var1 <= 191){
            lettre = "B";
        }
        if (this.var1 >= 192 && this.var1 <= 223){
            lettre = "C";
        }
        return lettre;
    }

    //Méthode qui permet d'identifier l'adresse réseau.
    public IpPackage adresseReseau() {
        int var1 = this.var1;
        int var2 = 0;
        int var3 = 0;
        byte var4 = 0;

        if (this.getClasse() == "B") {
           var2 = this.var2;
        }
        else if (this.getClasse() == "C") {
           var2 = this.var2;
           var3 = this.var3;
        }

        return new IpPackage(var1, var2, var3, var4);
    }
    
    //Méthode qui retourne une chaine de caractère qui contient l'adresse réseau.
    public String ToString(){
        return "Octet 1 : " + this.var1 + " /  Octet 2 : " + this.var2 + " / Octet 3 : " + this.var3+ " / Octet 4 : " + this.var4; 
    }

    //Méthode qui retourne true ou false selon si les adresses comparés sont les mêmes ou non.
    public Boolean estMemeReseau(IpPackage var1) {
      return this.adresseReseau().var1 == var1.adresseReseau().var1 && this.adresseReseau().var2 == var1.adresseReseau().var2 && this.adresseReseau().var3 == var1.adresseReseau().var3;
   }

}