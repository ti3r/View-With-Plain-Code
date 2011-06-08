/***
 *  View With Plain Code, a small application to demonstrate how to build
 *  and android view through java code.
 *  Copyright (C) 2011  Alexandro Blanco <ti3r.bubblenet@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.blanco.view_with_code;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LayoutParams layoutParams = new TableRow.LayoutParams(
        android.widget.TableRow.LayoutParams.FILL_PARENT,
        android.widget.TableRow.LayoutParams.WRAP_CONTENT
        );
        
        TableLayout tableView = new TableLayout(this);
        tableView.setColumnStretchable(0, true);
        tableView.setColumnStretchable(1, true);
        tableView.setColumnStretchable(2, true);
        
        TableRow row = null;
        List<Contact> contacts = buildContacts();
        for(Contact c:contacts){
        	
        	row = new TableRow(this);
        	row.setLayoutParams(layoutParams);
        	
        	TextView id = new TextView(this);
        	id.setId(111111);
        	id.setLayoutParams(layoutParams);
        	id.setText(String.valueOf(c.getId()));
        	row.addView(id);
        	
        	TextView nombre = new TextView(this);
        	nombre.setId(222222);
        	nombre.setLayoutParams(layoutParams);
        	nombre.setText(c.getNombre());
        	row.addView(nombre);
        	
        	TextView apellido = new TextView(this);
        	apellido.setId(333333);
        	apellido.setLayoutParams(layoutParams);
        	apellido.setText(c.getApellido());
        	row.addView(apellido);
        	
        	if (row != null)
        		tableView.addView(row);
        }
        setContentView(tableView);
    }
    
    
    public List<Contact> buildContacts(){
    	List<Contact> result = new ArrayList<MainActivity.Contact>();
    	
    	Cursor c = getContentResolver().query(Contacts.People.CONTENT_URI, 
    			new String[]{Contacts.People._ID,Contacts.People.NAME, Contacts.People.DISPLAY_NAME}, 
    			null, null, null);
    	while(c.moveToNext()){
    		Contact contact = new Contact(c.getLong(c.getColumnIndex(Contacts.People._ID)), 
    				c.getString(c.getColumnIndex(Contacts.People.NAME)), 
    				c.getString(c.getColumnIndex(Contacts.People.DISPLAY_NAME)));
    		result.add(contact);
    	}
    	return result;
    }
    
    
    class Contact{
    	long _id;
    	String nombre;
    	String apellido;
    	
		public Contact(long _id, String nombre, String apellido) {
			super();
			this._id = _id;
			this.nombre = nombre;
			this.apellido = apellido;
		}
		public long getId() {
			return _id;
		}
		public void setId(long _id) {
			this._id = _id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
    }
}