package in.co.rays.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class DataValidator.
 * @author Iterator
 * @version 1.0
 */
public class DataValidator {
	
	/**
	 * Checks if is null.
	 *
	 * @param val the val
	 * @return true, if is null
	 */
	public static boolean isNull(String val)
	{
		if(val==null||val.trim().length()==0)
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
	/**
	 * Checks if is not null.
	 *
	 * @param val the val
	 * @return true, if is not null
	 */
	public static boolean isNotNull(String val)
	{
		return !isNull(val);
	}
	
    /**
     * Checks if is integer.
     *
     * @param val the val
     * @return true, if is integer
     */
    public static boolean isInteger(String val)
    {
//    	System.out.println("in d1 "+val+"d2");
    	if(isNotNull(val))
    	{
    		try{
    			
    		Integer.parseInt(val);
    		
    		return true;
    		}catch(NumberFormatException e)
    		{
    			e.printStackTrace();
    			return false;
    		}
    	}
    	else
    	{
		return false;
    	}
    }
    
    /**
     * Checks if is long.
     *
     * @param val the val
     * @return true, if is long
     */
    public static boolean isLong(String val)
    {
    	if(isNotNull(val))
    	{
    		try{
    		Long.parseLong(val);
    		return true;
    		}catch(NumberFormatException e)
    		{
    			return false;
    		}
    	}
    	else
    	{
		return false;
    	}
   }
    
    /**
     * Checks if is password.
     *
     * @param val the val
     * @return true, if is password
     */
    public static  boolean isPassword(String val) {

		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
    
    /**
     * Checks if is phone no.
     *
     * @param val the val
     * @return true, if is phone no
     */
    public static boolean isPhoneNo(String val)
    {
    	String regx="^[6-9][0-9]{9}$";
    	
    	if(isNotNull(val) && isPhoneNoLength(val))
    	{
    		
    		
    		try{
    			return val.matches(regx);
    		}
    		catch(NumberFormatException e)
    		{
    			return false;
    		}
    		
    		
    	}
    	else
    	{
		return false;
    	}
    }
    
    /**
     * Checks if is phone no length.
     *
     * @param val the val
     * @return true, if is phone no length
     */
    public static boolean isPhoneNoLength(String val)
    {
    	if(isNotNull(val) && val.trim().length()==10)
    	{
    		return true;
    	}
    	else
    	{
		return false;
    	}
    }
    
    /**
     * Checks if is email.
     *
     * @param email the email
     * @return true, if is email
     */
    public static  boolean isEmail(String email){
		String reg="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(isNotNull(email)){
			try{
				return email.matches(reg);
			}catch(Exception e){
				return false;
			}
		}
		else{
			return false;
		}
	}
   
    /**
     * Checks if is date.
     *
     * @param val the val
     * @return true, if is date
     */
    public static boolean isDate(String val) {
		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

    
    
    /**
     * Checks if is fname.
     *
     * @param val the val
     * @return true, if is fname
     */
    public static boolean isFname(String val) {
		String fnamereg = "^[a-zA-Z,.'-]+$";
		if (isNotNull(val)) {
			try {
				return val.matches(fnamereg);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}    
    
   
    
    /**
     * Checks if is roll no.
     *
     * @param val the val
     * @return true, if is roll no
     */
    public static boolean isRollNo(String val)
    {
    	String rollreg="([A-Za-z]{2,})+([0-9]{2,})";
    	if(isNotNull(val)) {
    	try{
    		return val.matches(rollreg);
    	}
    	catch(Exception e) {
    		return false;
    	}
    	}else {
    		return false;
    	}
    }
    
    /**
     * Checks if is valid age.
     *
     * @param val the val
     * @return true, if is valid age
     */
    public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
				Date userdate = format.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (java.text.ParseException e) {
				
			}
		}
		
		return pass;
	}
    
    /**
     * Check Password Length.
     *
     * @param val the val
     * @return true, if is password length
     */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
		
			
			return true;
		} else {
			return false;
		}
	}

}
