package com.banan.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class MainMenu extends Composite 
{
	public MainMenu(final DeckPanel p)
	{
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("main_menu");
		initWidget(panel);
		
		Button buttonSim = new Button("Ny simulering");
		buttonSim.addStyleName("menu_item");
		Button buttonRegister = new Button("Legg til konsulent");
		buttonRegister.addStyleName("menu_item");
		Button buttonLogout = new Button("Logg ut");
		buttonLogout.addStyleName("menu_item");
		
		buttonSim.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) 
			{				
				p.showWidget(UI.SIMULATION);
			}			
		});
		
		buttonRegister.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) 
			{
				p.showWidget(UI.REGISTRATION);
			}			
		});
		
		buttonLogout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) 
			{				
				p.showWidget(UI.LOGIN);
			}			
		});
		
		panel.add(buttonSim);
		panel.add(buttonRegister);
		//panel.add(buttonLogout);
	}
}