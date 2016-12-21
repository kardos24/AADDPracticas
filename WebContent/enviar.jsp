<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enviar Mensajes</title>
</head>
<body>
<f:view>
<h:form id="ShippingForm">
<h:outputLabel value="Texto:" for="texto" />
<h:inputText id="texto" value="#{beanMensajes.texto}">
<f:validateLength maximum="255" />
</h:inputText><br/>
<font color="red"><h:message for="texto" /></font><br/>
<h:commandButton id="submit"
actionListener="#{beanMensajes.enviarTexto}" value="Enviar" /><br/>
<h:commandButton id="volver" action="index" value="Volver"
/>
</h:form>
</f:view>
</body>
</html>