package OOP;


import java.text.*;
import java.util.*;

public class Tiempo {
    private Date date;
    private DateFormat d = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    public Tiempo() {
    	date = new Date();
    	d.format(date);
    }

    
    /*
     * Modificacion del metodo equals para comparar dos tiempos
     * resultados:
     * 	0 --> son iguales
     * 	1 --> este objeto es mas nuevo
     * 	-1 --> el objeto a comparar es mas nuevo
     */
    public int equals(Tiempo t1){
		int result;
		
		if(this.date.before(t1.getDate())) {
			result = -1;
		}else if(this.date.after(t1.getDate())) {
			result = 1;
		}else result = 0;

		return result;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(String fecha) throws ParseException {
		Date a = this.d.parse(fecha);
		this.date = a;
	}


	@Override
	public String toString() {
		return ""+d.format(date);
	}

	
	
}

