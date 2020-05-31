import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld", eager = true)
public class HelloWorld {
    public HelloWorld(){
        System.out.println("HellWorld started!");
    }

    public String getMassage(){
        return "Fuck JSF! This is sheet";
    }
}
