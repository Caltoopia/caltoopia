#Get from code generator generated config.mk
#RUNTIME_ROOT = <runtimeroot>
#EXEC_NAME = <execname>
#SOURCES = <sources>
#CINCLUDES = <includepaths>
#CLIBRARIES = <librarypaths>
#LDLIBS = <libraries>
#SDL=<y/n>
#CC potentially specifying compiler
#TARGET = <linux/mac>

include config.mk

# delete the built-in suffixes to avoid surprises
.SUFFIXES:   

ifeq ($(DPRINT), y)
CFLAGS += -DDPRINT
endif
ifeq ($(SDL), y)
ifeq ($(TARGET), linux)
SOURCES += $(RUNTIME_ROOT)/share/sysactors/c/display-sdl.c
LDLIBS += `pkg-config --libs sdl`
CFLAGS += `pkg-config --cflags sdl`
endif
ifeq ($(TARGET), mac)
SOURCES += $(RUNTIME_ROOT)/share/sysactors/c/display-file.c
endif
endif

OBJECTS=$(SOURCES:.c=.o)
LDLIBS += -lactors-rts -lpthread -lxml2 
ifeq ($(TARGET), linux)
LDLIBS += -ldl -lm -lrt
endif
CFLAGS += -g -c -DTRACE
CINCLUDES += -I$(RUNTIME_ROOT)/include
CLIBRARIES += -L$(RUNTIME_ROOT)/lib

.PHONY: all clean

all: $(SOURCES) $(EXEC_NAME)

$(EXEC_NAME): $(OBJECTS)
	$(CC) $(OBJECTS) $(LDFLAGS) $(LDLIBS) $(CLIBRARIES) -o $@

$(OBJECTS): %.o : %.c
	$(CC) -Wall -Wno-unused-variable -Wno-missing-braces $(CFLAGS) $(CINCLUDES) -o $@ $<

clean:
	@rm -f *.o $(EXEC_NAME) *~

