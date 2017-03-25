package git;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSelenium {
	public static void main(String[] args) {
		File file=new File("d:\\TH\\inputgit.csv");
		List<String> git=importCsv(file);
		ArrayList<String> bool=new ArrayList<String>();
		
		System.setProperty("webdriver.firefox.bin", "D:\\fox_and_selenium\\Firefox24.0_9ht\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
		for(int i = 1;i<git.size();i++){
			driver.get("http://121.193.130.195:8080/");
			WebElement username=driver.findElement(By.name("name"));
			WebElement pwd=driver.findElement(By.name("pwd"));
			String userid=git.get(i).split(",")[0];
			String gitPath=git.get(i).split(",")[2];
			username.sendKeys(userid);
			pwd.sendKeys(userid.substring(4, 10));
			username.submit();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement table=driver.findElement(By.id("table-main"));
			if(gitPath.equals(table.findElement(By.xpath("//table//tr[3]//td[2]")).getText())){
				git.set(i,git.get(i).split(",")[0]+","+git.get(i).split(",")[1]+","+git.get(i).split(",")[2]+",true");
			}
			else{
				git.set(i,git.get(i).split(",")[0]+","+git.get(i).split(",")[1]+","+git.get(i).split(",")[2]+",false");
			}
		}
		
		exportCsv(file,git);
		driver.quit();
	}
	
	public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
	
    public static List<String> importCsv(File file){
        List<String> dataList=new ArrayList<String>();
        
        BufferedReader br=null;
        try { 
            br = new BufferedReader(new FileReader(file));
            String line = ""; 
            while ((line = br.readLine()) != null) { 
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return dataList;
    }
}
