/*
 * Exec.cpp
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
 *
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

#include <core/Exec.hpp>

#include <core/Error.hpp>

namespace core {

ExecBlock& ExecBlock::add(Function function) 
{ 
   functions_.push_back(function); 
   return *this;
}   
   
Error ExecBlock::execute() const
{
   for (std::vector<Function>::const_iterator
            it = functions_.begin(); it != functions_.end(); ++it)
   {
      Error error = (*it)();
      if (error)
         return error ;
   }
   return Success();
}
   
Error ExecBlock::operator()() const 
{  
   return execute(); 
}
   
} // namespace core 



