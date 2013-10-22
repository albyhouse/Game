package AutoRepair;
public class AutoRepairObject
{
  private String desc;
  private double cost;
  private int time;
  
  public AutoRepairObject (String d, double c, int t)
  {
    desc = d;
    cost = c;
    time = t;
  }//end constructor
  
  public String getDesc ()
  {
    return this.desc;
  }//end getter
  
  public double getCost ()
  {
    return this.cost;
  }//end getter
  
  public int getTime ()
  {
    return this.time;
  }//end getter
  
  public void outputString()
  {
    System.out.printf("%-22s%10s%8s\n",desc,"$"+cost,time);
  }//end toString method
  
}//end class