import { useState } from 'react'

const SAMPLE_PAYLOAD = {
  vehicles: [
    { model: "Sedan X1", plantLocation: "Detroit", productionDate: "2025-03-15" },
    { model: "SUV Y2", plantLocation: "Michigan", productionDate: "2025-03-16" },
  ],
  inventory: [
    { vehicleId: 1, status: "IN_STOCK", warehouseLocation: "Warehouse A" },
    { vehicleId: 2, status: "IN_TRANSIT", warehouseLocation: "Warehouse B" },
  ],
  dealers: [
    { name: "Metro Auto Group", region: "North" },
    { name: "Coastal Motors", region: "South" },
  ],
  productionMetrics: [
    { vehicleId: 1, productionTime: 45.5, defectsCount: 0 },
    { vehicleId: 2, productionTime: 52.3, defectsCount: 1 },
  ],
}

export default function ETLPanel({ apiBase, onSuccess }) {
  const [loading, setLoading] = useState(false)
  const [result, setResult] = useState(null)
  const [error, setError] = useState(null)

  const runETL = async () => {
    setLoading(true)
    setResult(null)
    setError(null)
    try {
      const res = await fetch(`${apiBase}/etl/run`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(SAMPLE_PAYLOAD),
      })
      const data = await res.json()
      if (!res.ok) throw new Error(data.message || 'ETL failed')
      setResult(data)
      onSuccess?.()
    } catch (e) {
      setError(e.message || 'Could not connect to API')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      <h2 className="text-2xl font-bold text-slate-800 mb-4">Load Sample Data (ETL)</h2>
      <div className="bg-white rounded-xl shadow p-6 max-w-md">
        <p className="text-slate-600 mb-4">
          Click to load sample vehicles, inventory, dealers, and production metrics into the database.
        </p>
        <button
          onClick={runETL}
          disabled={loading}
          className="px-6 py-3 bg-blue-600 text-white font-medium rounded-lg hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {loading ? 'Loading...' : 'Run ETL'}
        </button>
        {error && <p className="mt-4 text-red-600">{error}</p>}
        {result && (
          <div className="mt-4 p-4 bg-green-50 rounded-lg text-green-800">
            <p className="font-semibold">{result.status}</p>
            <p className="text-sm mt-1">Vehicles: {result.vehiclesLoaded} | Inventory: {result.inventoryLoaded} | Dealers: {result.dealersLoaded}</p>
          </div>
        )}
      </div>
    </div>
  )
}
