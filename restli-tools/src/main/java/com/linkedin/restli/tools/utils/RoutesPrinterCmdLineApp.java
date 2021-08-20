/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.linkedin.restli.tools.utils;


import com.linkedin.restli.internal.tools.AdditionalDocProvidersUtil;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Tool that serializes a set of resource models to a RESTspec IDL file
 *
 * Usage: restliexporter -help
 *
 * @author dellamag
 */
// OptionBuilder usage requires static-access suppression :/
public class RoutesPrinterCmdLineApp
{
  private static final Logger log = LoggerFactory.getLogger(RoutesPrinterCmdLineApp.class);

  private static final Options OPTIONS = new Options();

  /**
   * @param args restliexporter -sourcepath sourcepath -resourcepackages packagenames [-name api_name] [-outdir outdir]
   */
  public static void main(String[] args)
  {
//    args = new String[] {"-name", "groups",
//                         "-resourcepackages", "com.linkedin.groups.server.rest1.impl com.linkedin.groups.server.rest2.impl ",
//                         "-resourceclasses", "com.linkedin.groups.server.restX.impl.FooResource",
//                         "-sourcepath", "src/main/java",
//                         "-outdir", "src/codegen/idl",
//                         "-split"};

    CommandLine cl = null;
    try
    {
      final CommandLineParser parser = new GnuParser();
      cl = parser.parse(OPTIONS, args);
    }
    catch (ParseException e)
    {
      System.err.println("Invalid arguments: " + e.getMessage());
      final HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("restliexporter -sourcepath sourcepath [-resourcepackages packagenames] [-resourceclasses classnames]" +
          "[-name api_name] [-outdir outdir]", OPTIONS);
      System.exit(0);
    }

    try
    {
      RoutesPrinter.print();
    }
    catch (Throwable e)
    {
      log.error("Error writing IDL files", e);
      System.exit(1);
    }
  }
}
