import java.util.*;
public class Task{
	public final int id; 
	public final Date date;
	public final String city;
	private String weather;
	public Task(int id, String city, Date date ){
		this.id = id;
		this.date = date;
		this.city = city;
	}
	
	public void printTask(){
		System.out.println(id);
		System.out.println(date);
		System.out.println(city);
        }
        public int getId() {
            return id;
        }
        public Date getDate() {
            return date;
        }
        public String getCity() {
            return city;
        }
        public String getWeather() {
            return weather;
        }
        public void setWeather(String tmpWeather){
            this.weather = tmpWeather;
        }
}