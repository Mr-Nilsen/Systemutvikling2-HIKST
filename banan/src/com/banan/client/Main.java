package com.banan.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.banan.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class Main implements EntryPoint 
{
	private final UserServiceAsync UserService = GWT.create(UserService.class);
	
	public void onModuleLoad() 
	{
		final DeckPanel mainPanel = new DeckPanel();
		RootPanel.get("main_content").add(mainPanel);
		VerticalPanel introPanel = new VerticalPanel();
		mainPanel.add(introPanel);
		
		final DeckPanel menuPanel = new DeckPanel();
		RootPanel.get("menu").add(menuPanel);
		menuPanel.setVisible(false);
		
		MainMenu menu = new MainMenu(mainPanel);
		menuPanel.add(menu);	
		
		final Login login = new Login();
		VerticalPanel p = new VerticalPanel();
		p.add(login);
		mainPanel.add(p);
		
		final Registration register = new Registration();
		p = new VerticalPanel();
		p.add(register);
		mainPanel.add(p);
		
		login.addLoginHandler(new ActionHandler() {
			public void onAction()
			{
				UserService.login(login.getUsername(), login.getPassword(),
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) 
						{
							Window.alert(caught.getMessage());
						}
	
						public void onSuccess(String result) 
						{
							if (result.equals("OK"))
							{
								menuPanel.setVisible(true);
								mainPanel.showWidget(UI.INTRO);
							}
							else
							{
								Window.alert(result);
							}
						}
					});		
			}
		});
		
		register.addRegisterHandler(new ActionHandler() {
			public void onAction()
			{
				UserService.register(register.getFullName(), register.getUsername(), register.getPassword(),
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) 
						{
							Window.alert(caught.getMessage());
						}
	
						public void onSuccess(String result) 
						{
							Window.alert(result);
						}
					});	
			}
		});
		
		HTML intro = new HTML("<div class=\"foo\"><b>Du</b> er logget inn!</div>");
		introPanel.add(intro);
		
		HTML sim = new HTML("<div class=\"foo\"><img src=\"sim.png\" width=\"800\" height=\"400\" /></div>");
		mainPanel.add(sim);

		menuPanel.showWidget(UI.MAIN_MENU);	
		mainPanel.showWidget(UI.LOGIN);
	}
}