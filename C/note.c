-  Question 1: Difficult C code to understand

   //////////////////////////////////////////////////////////////////////////
   #define __CRC_SYMBOL(sym, sec)					\
      extern void *__crc_##sym __attribute__((weak));		\
      static const unsigned long __kcrctab_##sym		\
      __attribute_used__					\
      __attribute__((section("__kcrctab" sec), unused))	\
      = (unsigned long) &__crc_##sym;
   #else
   #define __CRC_SYMBOL(sym, sec)
   #endif

   /* For every exported symbol, place a struct in the __ksymtab section */
   #define __EXPORT_SYMBOL(sym, sec)				\
      extern typeof(sym) sym;					\
      __CRC_SYMBOL(sym, sec)					\
      static const char __kstrtab_##sym[]			\
      __attribute__((section("__ksymtab_strings")))		\
      = MODULE_SYMBOL_PREFIX #sym;                    	\
      static const struct kernel_symbol __ksymtab_##sym	\
      __attribute_used__					\
      __attribute__((section("__ksymtab" sec), unused))	\
      = { (unsigned long)&sym, __kstrtab_##sym }

   #define EXPORT_SYMBOL(sym)					\
      __EXPORT_SYMBOL(sym, "")
   //////////////////////////////////////////////////////////////////////////

   Q1: What is "__" meaning here?
   Q2: What is "##" meaning here?
   Q3: 

-  Question 2: What is the result of the following subtraction calculation?

   stack_p->top_p - stack_p->base_p >= stack_p->stacksize

   where top_p is a int pointer and base_p is also a int pointer.

-  Question 3: What is the following:

   #if defined(BFC_INCLUDE_ITC_SUPPORT)

   it only checks whether the flag is defined or not. It doesn't matter what value the flag is set if it is defined.
