/**
 * The MIT License
 * Copyright (c) 2015 Teal Cube Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package se.ranzdo.bukkit.methodcommand;

import org.bukkit.command.CommandSender;


public class FlagArgument extends CommandArgument {
	private final Flag flag;
	
	public FlagArgument(Arg commandArgAnnotation, Class<?> argumentClass, ArgumentHandler<?> argumentHandler, Flag flag) {
		super(commandArgAnnotation, argumentClass, argumentHandler);
		this.flag = flag;
	}
	
	public FlagArgument(String name, String description, String def, String verifiers, Class<?> argumentClass, ArgumentHandler<?> handler, Flag flag) {
		super(name, description, def, verifiers, argumentClass, handler);
		this.flag = flag;
	}

	@Override
	public Object execute(CommandSender sender, Arguments args) throws CommandError {
		String arg;
		if(!args.flagExists(flag))
			arg = getDefault();
		else if(!args.hasNext(flag))
			throw new CommandError("The argument s ["+getName()+"] to the flag -"+flag.getIdentifier()+" is not defined");
		else
			arg = CommandUtil.escapeArgumentVariable(args.nextFlagArgument(flag));
		
		return getHandler().handle(sender, this, arg);
	}
	
	public Flag getFlag() {
		return flag;
	}
}
