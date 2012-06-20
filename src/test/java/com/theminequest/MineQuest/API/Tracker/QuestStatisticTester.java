package com.theminequest.MineQuest.API.Tracker;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.alta189.simplesave.Database;
import com.alta189.simplesave.DatabaseFactory;
import com.alta189.simplesave.exceptions.ConnectionException;
import com.alta189.simplesave.exceptions.TableRegistrationException;
import com.alta189.simplesave.h2.H2Configuration;
import com.alta189.simplesave.mysql.MySQLConfiguration;
import com.alta189.simplesave.sqlite.SQLiteConfiguration;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Utils.PropertiesFile;

public class QuestStatisticTester {
	
	private StatisticManager statisticManager;

	@Before
	public void setUp() throws Exception {
		statisticManager = new Statistics();
	}

	@Test
	public void test() {
		statisticManager.registerStatistic(QuestStatistic.class);
	}
	
	
	
	public static class Statistics implements StatisticManager {

		private enum Mode{
			MySQL, SQlite, H2;
		}
		private Mode databasetype;
		private Database backend;
		
		public Statistics() throws ConnectionException, IOException{
			Managers.log("[SQL] Loading and connecting to SQL...");
			databasetype = Mode.H2;
			File f = File.createTempFile("tmpdb_", ".h2.db");
			backend = DatabaseFactory.createNewDatabase(new H2Configuration().setDatabase(f.getAbsolutePath().substring(0, f.getAbsolutePath().length()-7)));
		}


		@Override
		public Database getStorageBackend() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void connect(boolean connect) throws ConnectionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <T extends Statistic> T getStatistic(String playerName,
				Class<? extends Statistic> tableClazz) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T extends Statistic> void setStatistic(T statistic,
				Class<? extends Statistic> tableClazz) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void registerStatistic(Class<? extends Statistic> tableClazz) {
			try {
				backend.registerTable(tableClazz);
			} catch (TableRegistrationException e) {
				throw new RuntimeException(e);
			}
		}
		
	}

}
