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
CALVINCFLAGS += -DDPRINT
endif
ifeq ($(TARGET), linux)
ifeq ($(SDL), y)
SOURCES += $(RUNTIME_ROOT)/share/sysactors/c/display-sdl.c
LDLIBS += `pkg-config --libs sdl`
CFLAGS += `pkg-config --cflags sdl`
endif
ACTOROBJS=$(ACTORS:.c=.so)
endif
ifeq ($(TARGET), mac)
ifeq ($(SDL), y)
SOURCES += $(RUNTIME_ROOT)/share/sysactors/c/display-file.c
endif
ACTOROBJS=$(ACTORS:.c=.bundle)
endif

OBJECTS=$(SOURCES:.c=.o)
LDLIBS += -lactors-rts -lpthread -lxml2 
ifeq ($(TARGET), linux)
LDLIBS += -ldl -lm -lrt
endif
CFLAGS += -g -c -DTRACE
CINCLUDES += -I$(RUNTIME_ROOT)/include
CLIBRARIES += -L$(RUNTIME_ROOT)/lib
CALVINCFLAGS += -DCAL_RT_CALVIN -g

.PHONY: all clean legacy calvin

all: legacy

calvin: clean $(ACTOROBJS)

legacy: clean $(SOURCES) $(EXEC_NAME)

$(EXEC_NAME): $(OBJECTS)
	$(CC) $(OBJECTS) $(LDFLAGS) $(LDLIBS) $(CLIBRARIES) -o $@

$(OBJECTS): %.o : %.c
	$(CC) -std=c99 -Wall -Wno-unused-variable -Wno-missing-braces $(CFLAGS) $(CINCLUDES) -o $@ $<

clean:
	@rm -f *.bundle *.so *.o $(EXEC_NAME) *~

ifeq ($(TARGET), linux)
$(ACTOROBJS): %.so : %.c
	$(CC) -I$(CALVIN_RUNTIME_ROOT) $(CALVINCFLAGS) -std=c99 -Wall -Wno-unused-variable -Wno-missing-braces -fPIC -shared -Wl,-soname,$@ -o $@ $< commonSpec.c
endif

ifeq ($(TARGET), mac)
$(ACTOROBJS): %.bundle : %.c
	$(CC) -I$(CALVIN_RUNTIME_ROOT) $(CALVINCFLAGS) -std=c99 -Wall -Wno-unused-variable -Wno-missing-braces -fPIC -flat_namespace -bundle -undefined suppress -o $@ $< commonSpec.c
endif

