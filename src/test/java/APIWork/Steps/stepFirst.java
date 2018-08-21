package APIWork.Steps;

import APIWork.Entities.interns;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.sql.*;

import static net.serenitybdd.rest.SerenityRest.given;

public class stepFirst {
    @Step
    public void verifyAPI(){
        given().contentType("appication/json").when().get("http://localhost:3000/")
                .then().assertThat().statusCode(200)
                .extract().response().body().print();
    }


    @Step
    public void insertToDataBase(){
        interns intern = (interns) Serenity.getCurrentSession().get("intern");
        try {
            Connection con = connect();
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = stmt
                    .executeQuery("insert into test.interns values ( '"
                            +intern.getId()+","+intern.getName()+","+intern.getLastName()+","+intern.getAge()+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Step
    public  void FillData(){
        interns intern = new interns();
        intern.setAge("21");
        intern.setId(100);
        intern.setLastName("Torres");
        intern.setName("Maria");
        Serenity.getCurrentSession().put("intern",intern);
    }

    public Connection connect(){
        Connection connection =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3000/test","root","");
        }
        catch (Exception e) {
            System.out.println("Ups no connection to db");
        }
        return connection;
    }

    @Step
    public void compareInfo(){
        interns intern = (interns) Serenity.getCurrentSession().get("intern");
        String data = given().contentType("appication/json").when().get("http://localhost:3000/api/interns/"+intern.getId())
                .then().assertThat().statusCode(200)
                .extract().response().body().print();
        Assert.assertEquals("The result is not the expected one",data,intern.toString());
    }
}
