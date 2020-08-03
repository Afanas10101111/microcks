/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
export class TestRequest {
  serviceId: string;
  testEndpoint: string;
  runnerType: TestRunnerType;
  operationsHeaders: any;
}

export class TestResult {
  id: string;
  version: number;
  testNumber: number;
  testDate: Date;
  testedEndpoint: string;
  serviceId: string;
  elapsedTime: number;
  success: boolean = false;
  inProgress: boolean = true;
  runnerType: TestRunnerType;
  testCaseResults: TestCaseResult[];
}

export class TestCaseResult {
  success: boolean = false;
  elapsedTime: number = -1;
  operationName: string;
  testStepResults: TestStepResult[];
}

export class TestStepResult {
  success: boolean = false;
  elapsedTime: number;
  requestName: string;
  message: string;
}

export enum TestRunnerType {
  HTTP,
  SOAP_HTTP,
  SOAP_UI,
  POSTMAN,
  OPEN_API_SCHEMA
}