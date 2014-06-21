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
package net.bhira.sample.common;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * JUnit test class for JsonUtil.
 * 
 * @author Baldeep Hira
 */
public class JsonUtilTest {

	/**
	 * Test method for {@link net.bhira.sample.common.JsonUtil#createGson()}.
	 */
	@Test
	public void testCreateGson() {
		Gson gson = JsonUtil.createGson();
		assertNotNull(gson);

		// get current date and format it in UTC timezone and UTC date format
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(JsonUtil.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(JsonUtil.TIMEZONE));
		String nowStr = formatter.format(now);

		// Make sure you remove the millisecond portion as the date format
		// being used truncates milliseconds.  Add quotes around the string value
		// to be consistent with JSON output value for Date element.
		try {
			now = formatter.parse(nowStr);
		} catch (ParseException pex) {
			throw new RuntimeException(pex);
		}
		nowStr = "\"" + nowStr + "\"";

		// assert the serialized date value
		String serializedDate = gson.toJson(now);
		System.out.println("nowStr           = " + nowStr);
		System.out.println("serializedDate   = " + serializedDate);
		assertEquals(nowStr, serializedDate);

		System.out.println();
		
		// assert the deserialized date value
		Date deserializedDate = gson.fromJson(nowStr, Date.class);
		System.out.println("now              = " + now);
		System.out.println("deserializedDate = " + deserializedDate);
		assertEquals(now, deserializedDate);
	}

}
