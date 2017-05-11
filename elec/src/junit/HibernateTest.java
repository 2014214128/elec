package junit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.itcast.elec.domain.ElecText;

public class HibernateTest {
	
	@Test
	public void testElecText() {
		Configuration config  = new Configuration().configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session  = sf.openSession();
		session.beginTransaction();
		
		ElecText elecText = new ElecText();
		elecText.setTextName("≤‚ ‘hibernate√˚≥∆");
		elecText.setTextDate(new Date());
		elecText.setTextRemark("≤‚ ‘hibernate±∏◊¢");
		session.save(elecText);
		session.getTransaction().commit();
		session.close();
	}
}
