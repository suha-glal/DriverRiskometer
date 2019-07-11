/*
 * MLogCallBack.h
 *
 *  Created on: Dec 28, 2009
 *      Author: Suha
 */

#ifndef MLOGCALLBACK_H_
#define MLOGCALLBACK_H_
class MLogCallBack
{
    public:
    	
    	virtual void HandleLogEventL(const CLogEvent& event)=0;
        virtual void LogProcessed(TInt aError) = 0;
};

#endif /* MLOGCALLBACK_H_ */
