/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Baldeep Hira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.bhira.sample.api.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Class providing some JSON utilities.
 * 
 * @author Baldeep Hira
 */
public class JsonUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final String TIMEZONE = "UTC";

	/**
	 * Create a customized version of Gson that handles Date formats and timezones correctly. It
	 * uses UTC date format for parsing and formating dates and expects the date string values to be
	 * formatted in UTC timezone.
	 * 
	 * @return an instance of {@link com.google.gson.Gson}.
	 */
	public static Gson createGson() {

		// create a custom serializer for dates
		JsonSerializer<Date> serializer = new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				if (src == null) {
					return null;
				}
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
				formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
				return new JsonPrimitive(formatter.format(src));
			}
		};

		// create a custom de-serializer for dates
		JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				if (json == null) {
					return null;
				}
				String date = json.getAsString();
				SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
				formatter.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
				try {
					return formatter.parse(date);
				} catch (ParseException e) {
					throw new JsonParseException("Error parsing date " + date, e);
				}

			}
		};

		// create a custom gson that uses the custom date serializer/deserializer
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, serializer);
		gsonBuilder.registerTypeAdapter(Date.class, deserializer);
		return gsonBuilder.create();
	}

}