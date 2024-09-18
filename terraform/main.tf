terraform {
  backend "azurerm" {
    resource_group_name = "teraformDemo"
    storage_account_name = "tfstract"
    container_name = "tfcontainer"
    key="mydemovault.vaultstate"
  }
}
provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "rgsa1" {
  name     = "mydemovault-resource-group"
  location = "central US"
}

resource "azurerm_kubernetes_cluster" "aks1" {
  name                = "mydemovault-cluster-1"
  location            = azurerm_resource_group.rgsa1.location
  resource_group_name = azurerm_resource_group.rgsa1.name
  dns_prefix          = "mydemovaultcluster"

  default_node_pool {
    name       = "default"
    node_count = 1
    vm_size    = "Standard_DS2_v2"
  }

  identity {
    type = "SystemAssigned"
  }

  tags = {
    Environment = "Development"
  }
}
data "azurerm_container_registry" "acr" {
  name                = "terafromDemoRegistry" # Replace with your ACR name
  resource_group_name = "teraformDemo"
}

output "kubeconfig" {
  value = azurerm_kubernetes_cluster.aks1.kube_config_raw
  sensitive = true
}
