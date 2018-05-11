package com.OE.dao;

import com.OE.Beans.Admin;
import com.OE.Beans.User;

public interface DaoAdmin {
	boolean AdmUp(Admin adm);
	Admin AdmSea(String name);
}
