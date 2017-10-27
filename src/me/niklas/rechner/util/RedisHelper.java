package me.niklas.rechner.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.niklas.rechner.gui.Window;
import me.niklas.rechner.gui.WindowManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisHelper {

	private static JedisPool pool;
	private static int database;
	
	public static void connect(String host) {
		connect(host, 6379, "");
	}
	
	public static void connect(String host, int port) {
		connect(host, port, "");
	}
	
	@SuppressWarnings("resource")
	public static void connect(String host, int port, String password) {
		if(password.equals("")) {
			pool = new JedisPool(new JedisPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT);
		} else {
			pool = new JedisPool(new JedisPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, password);
		}
		
		Jedis redis = null;
		try {
			redis = pool.getResource();
			redis.select(0);
			database = 151;
		} catch (JedisConnectionException e) {
			if (redis != null) {
				redis = null;
			}
			WindowManager.setWindow(Window.ERROR);
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
	}
	
	public static void disconnect() {
		pool.destroy();
	}
	
	public static JedisPool getPool() {
		return pool;
	}
	
	public static int getDatabase() {
		return database;
	}
	
	@SuppressWarnings("resource")
	public static String get(String key) {
		Jedis redis = null;
		try {
			redis = pool.getResource();
			redis.select(database);
			return redis.get(key);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				redis = null;
			}
			WindowManager.setWindow(Window.ERROR);
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
		return "";
	}
	
	@SuppressWarnings("resource")
	public static void set(String key, String value) {
		Jedis redis = null;
		try {
			redis = pool.getResource();
			redis.select(database);
			
			if(value == null) {
				redis.del(key);
			} else redis.set(key, value);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				redis = null;
			}
			WindowManager.setWindow(Window.ERROR);
		} finally {
			if (redis != null) {
				redis.close();
			}
		}
	}
	
	public static boolean contains(String key) {
		String got = get(key);
		
		if(got != null) {
			if(got.equals("")) {
				return false;
			} else return true;
		} else return false;
	}
	
	@SuppressWarnings("resource")
	public static void sadd(String key, String... members) {
        Jedis redis = null;
        try {
            redis = pool.getResource();
            redis.select(database);

            redis.sadd(key, members);
        } catch (JedisConnectionException e) {
        	if (redis != null) {
				redis = null;
			}
        	WindowManager.setWindow(Window.ERROR);
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

	@SuppressWarnings("resource")
	public static void srem(String key, String... members) {
        Jedis redis = null;
        try {
            redis = pool.getResource();
            redis.select(database);

            redis.srem(key, members);
            
        } catch (JedisConnectionException e) {
        	if (redis != null) {
				redis = null;
			}
        	WindowManager.setWindow(Window.MAIN);
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }
	
	@SuppressWarnings({ "resource", "finally" })
	public static Object[] sgetAll(String key) {
		Set<String> list = null;
		Jedis redis = null;
		try {
            redis = pool.getResource();
            redis.select(database);

            list = redis.smembers(key);
            
        } catch (JedisConnectionException e) {
        	if (redis != null) {
				redis = null;
			}
        	WindowManager.setWindow(Window.MAIN);
        } finally {
            if (redis != null) {
                redis.close();
            }
            Object[] l =  list.toArray();
            return l;
        }
		
	}


    @SuppressWarnings("resource")
	public static void smembers(String key) {
        Jedis redis = null;
        try {
            redis = pool.getResource();
            redis.select(database);

            redis.smembers(key);
        } catch (JedisConnectionException e) {
        	if (redis != null) {
				redis = null;
			}
        	WindowManager.setWindow(Window.MAIN);
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    public static List<String> sort(String key, SortingParams params) {
        Jedis redis = null;
        try {
            redis = pool.getResource();
            redis.select(database);

            return redis.sort(key, params);
        } catch (JedisConnectionException e) {
        	WindowManager.setWindow(Window.MAIN);
        } finally {
            if (redis != null) {
                redis.close();
            }
        }

        return new ArrayList<String>();
    }

}

