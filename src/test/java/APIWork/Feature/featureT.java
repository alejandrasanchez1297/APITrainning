package APIWork.Feature;

import APIWork.Steps.stepFirst;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class featureT {

    @Steps
    stepFirst asAUser;
    @Test
    public void getInternFromId()
    {
        asAUser.verifyAPI();
        asAUser.insertToDataBase();
        asAUser.FillData();
        asAUser.compareInfo();



    }
}
