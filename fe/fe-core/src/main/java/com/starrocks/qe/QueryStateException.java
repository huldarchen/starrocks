// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.starrocks.qe;

import com.google.common.base.Strings;
import com.starrocks.common.StarRocksException;
import com.starrocks.qe.QueryState.MysqlStateType;

public class QueryStateException extends StarRocksException {
    private QueryState queryState;

    public QueryStateException(MysqlStateType stateType, String msg) {
        super(Strings.nullToEmpty(msg));
        createQueryState(stateType, msg);
    }

    public void createQueryState(MysqlStateType stateType, String msg) {
        this.queryState = new QueryState();
        switch (stateType) {
            case OK:
                queryState.setOk(0L, 0, msg);
                break;
            case ERR:
                queryState.setError(msg);
                break;
            case EOF:
                queryState.setEof();
                queryState.setMsg(msg);
                break;
            case NOOP:
                break;
        }
    }

    public QueryState getQueryState() {
        return queryState;
    }
}
